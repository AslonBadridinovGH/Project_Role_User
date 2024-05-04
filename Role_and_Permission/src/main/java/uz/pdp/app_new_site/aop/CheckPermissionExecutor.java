package uz.pdp.app_new_site.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.app_new_site.entity.User;
import uz.pdp.app_new_site.exceptions.ForbiddenException;


@Component
@Aspect
public class CheckPermissionExecutor {

    @Before(value ="@annotation(checkPermission)")
    public void checkUserPermissionMyMethod(CheckPermission checkPermission)
    {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exist = false;

        //  Stream<? extends GrantedAuthority>  stream =
        //  user.getAuthorities().stream().filter( grantedAuthority -> grantedAuthority.equals(checkPermission.permission()) );

        for (GrantedAuthority authority : user.getAuthorities())
        {
             if (authority.getAuthority().equals(checkPermission.permission()))
             {
              exist=true;
              break;
             }
        }

          if (!exist)
              throw new ForbiddenException(checkPermission.permission(), "No permission");
    }

}
