package uz.pdp.app_new_site.entity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.app_new_site.entity.enums.Permission;
import uz.pdp.app_new_site.entity.template.AbstractEntity;
import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User extends AbstractEntity implements UserDetails {


    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role role;

    private boolean  enabled;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<Permission> permissionList = this.role.getPermissionList();

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Permission permission : permissionList) {
           grantedAuthorities.add( new SimpleGrantedAuthority(permission.name()) );
        }
        return grantedAuthorities;
    }


    public User(String fullName, String username, String password, Role role, boolean enabled) {

        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

}
