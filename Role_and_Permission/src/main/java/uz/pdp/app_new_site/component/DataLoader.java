package uz.pdp.app_new_site.component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.app_new_site.entity.Role;
import uz.pdp.app_new_site.entity.User;
import uz.pdp.app_new_site.entity.enums.Permission;
import uz.pdp.app_new_site.repository.RoleRepository;
import uz.pdp.app_new_site.repository.UserRepository;
import uz.pdp.app_new_site.util.AppConstants;
import java.util.Arrays;
import static uz.pdp.app_new_site.entity.enums.Permission.*;    // static tarzda import qilib oldik

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Value("${spring.sql.init.mode}") // for always oder never in application.properties
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {

        if (initialMode.equals("always")){

        Permission[] permissions = Permission.values();

        // ADMIN has all permission
        Role admin = roleRepository.save(new Role(AppConstants.ADMIN, Arrays.asList(permissions),"System owner"));


        Role user = roleRepository.save(new Role( AppConstants.USER, Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_COMMENT), "Normal user"));

        userRepository.save(new User("Admin","admin",passwordEncoder.encode("admin123"),admin,true));
        userRepository.save(new User("User", "user", passwordEncoder.encode("user123"), user, true));

       }

    }


}
