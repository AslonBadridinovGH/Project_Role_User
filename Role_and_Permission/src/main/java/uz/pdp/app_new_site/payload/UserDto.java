package uz.pdp.app_new_site.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull(message = "fullName should not be empty")
    private  String fullName;

    @NotNull(message = "userName should not be empty")
    private  String userName;

    @NotNull(message = "Password should not be empty")
    private  String password;

    public UserDto(String fullName, String userName, Long roleId) {
        this.fullName = fullName;
        this.userName = userName;
        this.roleId = roleId;
    }

    @NotNull(message = "Role should not be empty")
    private Long roleId;

}

