package uz.pdp.app_new_site.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull(message = "fullName bo'sh bo'lmasin")
    private  String fullName;

    @NotNull(message = "userName bo'sh bo'lmasin")
    private  String userName;

    @NotNull(message = "Parol bo'sh bo'lmasin")
    private  String password;

    public UserDto(String fullName, String userName, Long lavozimId) {
        this.fullName = fullName;
        this.userName = userName;
        this.lavozimId = lavozimId;
    }

    @NotNull(message = "Lavozim bo'sh bo'lmasin")
    private Long lavozimId;
}
