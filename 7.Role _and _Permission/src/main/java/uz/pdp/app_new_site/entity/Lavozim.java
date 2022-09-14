package uz.pdp.app_new_site.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.app_new_site.entity.enums.Huquq;
import uz.pdp.app_new_site.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Lavozim  extends AbstractEntity {

    @Column(unique = true, nullable = false)// PROGRAMMA run BO'LGAN PAYTDA ADMIN VA USER BIR MARTA QO'SHILADI KEYINGI SAFAR QO'SHILMASIN
    private String name;                   // Admin, User yoki boshqalar

     /*@Enumerated(value = EnumType.STRING)
     private LavozimTurlari lavozimTurlari;*/

     @Enumerated(value = EnumType.STRING)
     @ElementCollection(fetch = FetchType.LAZY)
     private List<Huquq> huquqlarList;

     @Column(length=600)                       //  columnDefinition = "text"
     private String description;

}
