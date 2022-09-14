package uz.pdp.app_new_site.entity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.app_new_site.entity.enums.Huquq;
import uz.pdp.app_new_site.entity.template.AbstractEntity;
import javax.persistence.*;
import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")    // ADMIN TOMONIDAN QO'SHILADIGAN FOYDALANUVCHILAR YA'NI MAQOLANI O'QUVCHILAR YOKI MUHARRIR
public class User extends AbstractEntity implements UserDetails {


    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    // LAVOZIM VA UNING HUQUQLARINI QAYTARADI . BITTA LAVOZIMDA 5-6  KISHI ISHLASHI MUMKIN.
    // OLDINGI DARSDA ROLE LAR ENUM KO'RINISHDA EDI. BU YERDA ESA  HUQUQ ENUM KO'RINISHIDA, User ga ROLE ni String ko'rinishida beramiz
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lavozim  lavozim;


    // DINAMIC O'ZGARUVCHI
    private boolean  enabled;

    // UserDetails NING 7 TA METHODLARI BOR VA METHOD KONSTRUKTORLARI O'RNIDA @Data KELADI: getAuthorities(){},isAccountNonExpired(){},
    // isAccountNonLocked(){}, isCredentialsNonExpired(){}, getUsername(){}, getPassword(){}, isEnabled(){}.

    // BULAR STATIC  O'ZGARUVCHILAR
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;


    // Elementining Typi GrantedAuthority DAN extends olgan Collection qaytaruvchi Method
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<Huquq> huquqlarList = this.lavozim.getHuquqlarList();

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Huquq huquq : huquqlarList) {

         // 1- USUL: OLDINGI DARS/DAGI ENUMCLASS KABI GrantedAuthority ning getAuthority() methodini huquq.name()
         // qaytaradigan  qilamiz. CHUNKI U String QAYTARADI . VA SHUNDA GrantedAuthority huquq.name() ni QAYTARADI.
          // grantedAuthorities.add(  new GrantedAuthority(){ @Override public String getAuthority(){return huquq.name();}});
          // grantedAuthorities.add( (GrantedAuthority) () -> huquq.name());
          // grantedAuthorities.add( (GrantedAuthority) huquq::name);

           // YOKI  2- USUL: GrantedAuthority dan extend olgan SimpleGrantedAuthority Class ni qo'llaymiz
           grantedAuthorities.add( new SimpleGrantedAuthority(huquq.name()) );

        }
        return grantedAuthorities;
    }


    public User(String fullName, String username, String password, Lavozim lavozim, boolean enabled) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.lavozim = lavozim;
        this.enabled = enabled;
    }

}
