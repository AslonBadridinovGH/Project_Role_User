package uz.pdp.app_new_site.payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_new_site.entity.enums.Permission;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    @NotBlank
    private String   name;

    private String   description;

    @NotEmpty
    private List<Permission> permissionList;
}
