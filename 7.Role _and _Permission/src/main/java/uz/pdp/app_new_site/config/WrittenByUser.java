package uz.pdp.app_new_site.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.app_new_site.entity.User;

import java.util.Optional;

public class WrittenByUser implements AuditorAware<User>{

    @Override
    public Optional<User> getCurrentAuditor() {

        // KIMDIR SYSTEMAGA KIRIB TURGAN USER - null BÖLMASA, Authenticated BÖLSA, anonymousUser BÖLMASA
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
              if (authentication !=null
              && authentication.isAuthenticated()
              && !authentication.getPrincipal().equals("anonymousUser"))
        {
            User user = (User)authentication.getPrincipal();
            return Optional.of(user);
        }
        return Optional.empty();
    }}
