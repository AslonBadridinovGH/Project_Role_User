package uz.pdp.app_new_site.aop;

import jdk.nashorn.internal.ir.IfNode;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.app_new_site.entity.User;
import uz.pdp.app_new_site.exceptions.ForbiddenException;



                                    // aop- ASPECT ORIENTED PROGRAMMING
                                    // @PreAuthorize(value = "hasAuthority('ADD_LAVOZIM')")  ANOTATSIYASI O'RNIDA O'ZIMIZ
                                    // HuquqniTekshirish ANNOTATSIYASINI YARATAMIZ
                        //  @Aspect - natija
@Component              // CheckPermissionExcecutor: HuquqniTekshirish  Annotatsiyasini ishga tushiruvchi Class
@Aspect                 // bu class TIZIMGA KIRGAN User ning Authorities si huquqniTekshirish Annotatsiyasidagi valuega tengligini tekshiradi
public class CheckPermissionExcecutor {

     // QACHONKI huquqniTekshirish ANNOTATSIYASI CHAQIRILSA   checkUserPermissionMyMethod METHODI ISHLASIN
    @Before(value ="@annotation(huquqniTekshirish)")
    public void checkUserPermissionMyMethod(HuquqniTekshirish huquqniTekshirish)
    {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exist=false;

      //  Stream<? extends GrantedAuthority>  stream =
      //  user.getAuthorities().stream().filter( grantedAuthority -> grantedAuthority.equals(huquqniTekshirish.huquq()) );

        for (GrantedAuthority authority : user.getAuthorities())
        {
             if (authority.getAuthority().equals(huquqniTekshirish.huquq()))
             {
              exist=true;
              break;       // AGAR equals  BO'SA FOR SIKL TO'XTASIN
             }
        }
           // AGAR  equals BO'LMASA  ForbiddenException  ga OTIB ZUBORAMIZ
          if (!exist)
              throw new ForbiddenException(huquqniTekshirish.huquq(), "Ruxsat yo'q");
    }

}
