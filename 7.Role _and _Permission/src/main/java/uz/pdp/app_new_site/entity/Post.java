package uz.pdp.app_new_site.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.app_new_site.entity.template.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends AbstractEntity {

    // MAQOLA (POST)  NING FILEDLARI

    @Column(nullable = false, columnDefinition = "text")   // Title UZUN BOLISHI MUMIIN SHU U-N TEXT FORMAT BERAMIZ
    private String  title;

    @Column(nullable = false, columnDefinition = "text")
    private String  text;                                   // MAQOLANING ASOSIY QISMI

    @Column(nullable = false, columnDefinition = "text")
    private String  url;                                    // Maqola url ining oxirgi qismi, avtomatik yoziladi
}
