package uz.pdp.app_new_site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.app_new_site.entity.User;
import uz.pdp.app_new_site.exceptions.ResourceNotFoundException;
import uz.pdp.app_new_site.payload.ApiResponse;
import uz.pdp.app_new_site.payload.RegisterDto;
import uz.pdp.app_new_site.repository.LavozimRepository;
import uz.pdp.app_new_site.repository.UserRepository;
import uz.pdp.app_new_site.util.AppConstants;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LavozimRepository lavozimRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

                                       // COMMENT / NI ADD,EDIT,DELETE QILADIGAN USER NI QO'SHAMIZ
    public ApiResponse registerUser(RegisterDto registorDto) {

        if (!registorDto.getPassword().equals(registorDto.getPrePassword())){
            return new ApiResponse("Parollar mos emas",false);
        }

        if (userRepository.existsByUsername(registorDto.getUserName()))
            return new ApiResponse("Bunday username avval ro'yxatdan o'tgan",false);

         User user=new User( registorDto.getFullName(), registorDto.getUserName(),
        passwordEncoder.encode(registorDto.getPassword()),

        //  lavozimRepository dan Lavozim ni topolmasa shu  Exception (ResourceNotFoundException) ni qaytarishi kerak
        lavozimRepository.findByName(AppConstants.USER).orElseThrow(
         () -> new ResourceNotFoundException("lavozim", "name", AppConstants.USER)),true);

        userRepository.save(user);
        return new ApiResponse("Muvaffaqizatli ro'yxatdan o'tdingiz",true);
    }


    public UserDetails loadUserByUsername(String userName) {

       //  userRepository dan User ni topolmasa shu  UsernameNotFoundException  ni qaytarishi kerak
       return  userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(userName+" topilmadi"));

    }


}

