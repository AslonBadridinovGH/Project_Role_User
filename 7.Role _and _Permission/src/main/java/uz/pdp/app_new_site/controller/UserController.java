package uz.pdp.app_new_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_new_site.aop.HuquqniTekshirish;
import uz.pdp.app_new_site.entity.User;
import uz.pdp.app_new_site.payload.ApiResponse;
import uz.pdp.app_new_site.payload.PostDto;
import uz.pdp.app_new_site.payload.RegisterDto;
import uz.pdp.app_new_site.payload.UserDto;
import uz.pdp.app_new_site.repository.UserRepository;
import uz.pdp.app_new_site.service.AuthService;
import uz.pdp.app_new_site.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
// User /NI QO'SHISH MASALAN : muharrir, redaktor, oddiz foydalanuvchi.... FAQAT ADMIN QILA OLADI

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;



    // @PreAuthorize(value = "hasAuthority('ADD_USER')")
    @HuquqniTekshirish(huquq = "ADD_USER")
    @PostMapping("/addUser")        // @NotNull ishlashi u-n, @Valid anotatsizasi kerak
    public HttpEntity<?>registerUser(@Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse=userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    // @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @HuquqniTekshirish(huquq = "VIEW_USERS")
    @GetMapping("/users")
    public HttpEntity<?>getUsers(){
        List<UserDto>userDtos=new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userDtos.add(new UserDto(user.getFullName(),user.getUsername(),user.getLavozim().getId()));
        }
        return ResponseEntity.status(200).body(userDtos);
    }


    // @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @HuquqniTekshirish(huquq = "EDIT_USER")
    @PutMapping("/editUser/{id}")
    public HttpEntity<?>editPost(@PathVariable Long id, @Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse=userService.editUser(id,userDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    // @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @HuquqniTekshirish(huquq = "DELETE_USER")
    @DeleteMapping("/deleteUser/{id}")
    public HttpEntity<?>deleteUser(@PathVariable Long id){
        ApiResponse apiResponse=userService.deleteUser(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
