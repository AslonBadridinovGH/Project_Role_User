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
import uz.pdp.app_new_site.repository.RoleRepository;
import uz.pdp.app_new_site.repository.UserRepository;
import uz.pdp.app_new_site.util.AppConstants;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public ApiResponse registerUser(RegisterDto registorDto) {

        if (!registorDto.getPassword().equals(registorDto.getPrePassword())){
            return new ApiResponse("password error",false);
        }

        if (userRepository.existsByUsername(registorDto.getUserName()))
            return new ApiResponse("such username is already exist",false);

         User user=new User( registorDto.getFullName(), registorDto.getUserName(),
        passwordEncoder.encode(registorDto.getPassword()),

        roleRepository.findByName(AppConstants.USER).orElseThrow(
         () -> new ResourceNotFoundException("role", "name", AppConstants.USER)),true);

        userRepository.save(user);
        return new ApiResponse("successfully registered",true);
    }


    public UserDetails loadUserByUsername(String userName) {

       return  userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(userName+" not found"));

    }


}

