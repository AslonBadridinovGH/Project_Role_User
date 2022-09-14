package uz.pdp.app_new_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.app_new_site.entity.User;
import uz.pdp.app_new_site.payload.ApiResponse;
import uz.pdp.app_new_site.payload.LoginDto;
import uz.pdp.app_new_site.payload.RegisterDto;
import uz.pdp.app_new_site.security.JwtProvider;
import uz.pdp.app_new_site.service.AuthService;

import javax.validation.Valid;  // spring-boot-starter-validation KUTUBXONASIDAN OLADI

            // BU HAM UserController KABI AMMO BUNDA HAMMA YÖLLAR Security Config da OCHIQ BÖLADI PAROL LOGIN SÖRAMAYDI
@RestController
@RequestMapping("/api/auth")
public class AuthController {


     @Autowired
     AuthService authService;

     @Autowired
    JwtProvider jwtProvider;

     @Autowired
     AuthenticationManager authenticationManager;      // Username va Parolni bersa DB bilan o'zi solishtiradi

    // @NotNull ishlashi u-n, @Valid anotatsizasi kerak
    @PostMapping("/register")
    public HttpEntity<?>registerUser(@Valid @RequestBody RegisterDto registerDto){
        ApiResponse apiResponse=authService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    // @NotNull ishlashi u-n, @Valid anotatsizasi kerak
    @PostMapping("/login")
    public HttpEntity<?>loginUser(@Valid @RequestBody LoginDto loginDto) {

        try {
            // AuthenticationManager - Username va Parolni bersa DB bilan o'zi solishtiradi, TO'GRI BO'LSA USERNI TIZIMGA KIRITADI
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
            User user = (User) authenticate.getPrincipal();  // TIZIMGA KIRGAN BO'LSA OBJECT OLIB KELADI

            String token = jwtProvider.generateToken(user.getUsername(), user.getLavozim());
            return ResponseEntity.ok(token);

         } catch (BadCredentialsException badCredentialsException) {
            return ResponseEntity.ok("Login yoki parol xato");
        }
   }

}
