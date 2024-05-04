package uz.pdp.app_new_site.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_new_site.entity.enums.Permission;
import uz.pdp.app_new_site.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Role extends AbstractEntity {

      @Column(unique = true, nullable = false)
      private String name;


     @Enumerated(value = EnumType.STRING)
     @ElementCollection(fetch = FetchType.LAZY)
     private List<Permission> permissionList;

     @Column(length=600)   //  columnDefinition = "text"
     private String description;

}
