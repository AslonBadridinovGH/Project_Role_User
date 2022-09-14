package uz.pdp.app_new_site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.app_new_site.entity.User;
import uz.pdp.app_new_site.exceptions.ResourceNotFoundException;
import uz.pdp.app_new_site.payload.ApiResponse;
import uz.pdp.app_new_site.payload.UserDto;
import uz.pdp.app_new_site.repository.LavozimRepository;
import uz.pdp.app_new_site.repository.UserRepository;
import uz.pdp.app_new_site.util.AppConstants;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    LavozimRepository lavozimRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse addUser(UserDto userDto) {

        if (userRepository.existsByUsername(userDto.getUserName()))
            return new ApiResponse("Bunday username avval ro'yxatdan o'tgan",false);

        User user=new User( userDto.getFullName(), userDto.getUserName(),passwordEncoder.encode(userDto.getPassword()),

       // lavozimRepository.findById() OPTIONAL QAYTARADI BUNDA isPresent bo'lmasa orElseThrow ga o'tadi:
       lavozimRepository.findById(userDto.getLavozimId()).orElseThrow(
       () -> new ResourceNotFoundException("lavozim ", "with Id ", userDto.getLavozimId()+" not found")),true);
       userRepository.save(user);
       return new ApiResponse("Muvaffaqizatli saqlandi",true);
    }

    public ApiResponse editUser(Long id, UserDto userDto) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
         throw  new ResourceNotFoundException("User", "with id", id + " not found");
       //  userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "with id", id + " nit found"));
     }
        if (userRepository.existsByUsername(userDto.getUserName()))
            return new ApiResponse("Bunday username avval ro'yxatdan o'tgan",false);

          User user = byId.get();
        user.setFullName( userDto.getFullName());
        user.setUsername( userDto.getUserName());
        user.setPassword( passwordEncoder.encode(userDto.getPassword()));

        // lavozimRepository.findById() OPTIONAL QAYTARADI BUNDA isPresent bo'lmasa orElseThrow ga o'tadi:
        user.setLavozim(lavozimRepository.findById(userDto.getLavozimId()).orElseThrow(
                        () -> new ResourceNotFoundException("lavozim ", "with Id ", userDto.getLavozimId()+" not found")));
        user.setEnabled(true);
        userRepository.save(user);
        return new ApiResponse("Muvaffaqizatli o'zgartirildi",true);
    }

    public ApiResponse deleteUser(Long id) {

        try {
            userRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }catch (Exception e){
            // return new ApiResponse("not deleted",false);
            throw  new ResourceNotFoundException("Lavozim with Id: "+id,"not deleted", false);
        }

    }

}
