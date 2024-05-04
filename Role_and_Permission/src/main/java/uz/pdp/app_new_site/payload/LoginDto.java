package uz.pdp.app_new_site.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotNull(message = "userName should not be empty")
    private  String userName;

    @NotNull(message = "Password should not be empty")
    private  String password;

}
