package uz.pdp.app_new_site.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.pdp.app_new_site.entity.User;


@Configuration
@EnableJpaAuditing
public class WrittenByUserConfig {
      @Bean
      AuditorAware<User>auditorAware(){
       return new WrittenByUser();
   }
}
