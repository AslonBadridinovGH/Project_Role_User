package uz.pdp.app_new_site.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;  // spring-boot-starter-validation KUTUBXONASIDA OLADI

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @NotNull(message = "fullName bo'sh bo'lmasin")
    private  String fullName;

    @NotNull(message = "userName bo'sh bo'lmasin")
    private  String userName;

    @NotNull(message = "Parol bo'sh bo'lmasin")
    private  String password;

    @NotNull(message = "Parol takrori bo'sh bo'lmasin")
    private  String prePassword;



}
