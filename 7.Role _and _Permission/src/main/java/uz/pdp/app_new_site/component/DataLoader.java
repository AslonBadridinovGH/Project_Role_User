package uz.pdp.app_new_site.component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.app_new_site.entity.Lavozim;
import uz.pdp.app_new_site.entity.User;
import uz.pdp.app_new_site.entity.enums.Huquq;
import uz.pdp.app_new_site.repository.LavozimRepository;
import uz.pdp.app_new_site.repository.UserRepository;
import uz.pdp.app_new_site.util.AppConstants;
import java.util.Arrays;
import static uz.pdp.app_new_site.entity.enums.Huquq.*;    // static tarzda import qilib oldik

@Component
public class DataLoader implements CommandLineRunner {     // PROGRAMMA run B;LGAN PAZTDA BUZRUQLARNI SHU ZERDA BERISH MUMKIN

    @Autowired
    UserRepository userRepository;
    @Autowired
    LavozimRepository lavozimRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Value("${spring.datasource.initialization-mode}") // application.properties dagi qiymat: always oder never ni olib keladi.
    private String initialMode;

                    // spring.datasource.initialization-mode=never VA CommandLineRunner NING METHODI  YORDAMIDA
                    // Userlar va  Lavozim ni qo'shish usuli. YANA User VA Lavozim QO'SHISHNI ISTASAK AuthController VA
                    // LavozimContoller dan FOYDALANAMIZ.
    @Override
    public void run(String... args) throws Exception {

        if (initialMode.equals("always")){
        // PROGRAMMA RUN BO'LGANDA ADMIN VA ROLLARNI OCHIB QO'ZISH KERAK
        Huquq [] huquqs = Huquq.values();

        // ADMIN da hamma huquqlar bor
        Lavozim admin = lavozimRepository.save(new Lavozim(AppConstants.ADMIN, Arrays.asList(huquqs),"Tizim egasi"));


        // USER ning huquqlari: ADD_COMMENT, EDIT_COMMENT, DELETE_COMMENT
        // AppConstants.USER, Arrays.asList(Huquq.ADD_COMMENT, Huquq.EDIT_COMMENT, Huquq.DELETE_COMMENT)yoki pastdagidek static holatda:
        Lavozim user = lavozimRepository.save(new Lavozim( AppConstants.USER, Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_COMMENT),
                                                     "Oddiy foydalanuvchi"));

        userRepository.save(new User("Admin","admin",passwordEncoder.encode("admin123"),admin,true));
        userRepository.save(new User("User", "user", passwordEncoder.encode("user123"), user, true));


       }
    }


}
