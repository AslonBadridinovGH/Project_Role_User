package uz.pdp.app_new_site.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;  // spring-boot-starter-validation KUTUBXONASIDA OLADI

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @NotNull(message = "fullName should not be empty")
    private  String fullName;

    @NotNull(message = "userName should not be empty")
    private  String userName;

    @NotNull(message = "password should not be empty")
    private  String password;

    @NotNull(message = "prePassword should not be empty")
    private  String prePassword;



}
