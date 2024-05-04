package uz.pdp.app_new_site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.app_new_site.entity.User;
import uz.pdp.app_new_site.exceptions.ResourceNotFoundException;
import uz.pdp.app_new_site.payload.ApiResponse;
import uz.pdp.app_new_site.payload.UserDto;
import uz.pdp.app_new_site.repository.RoleRepository;
import uz.pdp.app_new_site.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse addUser(UserDto userDto) {

        if (userRepository.existsByUsername(userDto.getUserName()))
            return new ApiResponse("such username is already registered",false);

        User user=new User( userDto.getFullName(), userDto.getUserName(),passwordEncoder.encode(userDto.getPassword()),

       roleRepository.findById(userDto.getRoleId()).orElseThrow(
       () -> new ResourceNotFoundException("role ", "with Id ", userDto.getRoleId()+" not found")),true);
       userRepository.save(user);
       return new ApiResponse("successfully saved",true);
    }

    public ApiResponse editUser(Long id, UserDto userDto) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
         throw  new ResourceNotFoundException("User", "with id", id + " not found");
       //  userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "with id", id + " nit found"));
     }
        if (userRepository.existsByUsername(userDto.getUserName()))
            return new ApiResponse("such username is already registered",false);

          User user = byId.get();
        user.setFullName( userDto.getFullName());
        user.setUsername( userDto.getUserName());
        user.setPassword( passwordEncoder.encode(userDto.getPassword()));


        user.setRole(roleRepository.findById(userDto.getRoleId()).orElseThrow(
                        () -> new ResourceNotFoundException("role ", "with Id ", userDto.getRoleId()+" not found")));
        user.setEnabled(true);
        userRepository.save(user);
        return new ApiResponse("successfully edited",true);
    }

    public ApiResponse deleteUser(Long id) {

        try {
            userRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }catch (Exception e){
            // return new ApiResponse("not deleted",false);
            throw  new ResourceNotFoundException("role with Id: "+id,"not deleted", false);
        }

    }

}
