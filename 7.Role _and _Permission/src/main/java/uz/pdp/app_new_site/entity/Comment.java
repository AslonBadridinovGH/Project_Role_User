package uz.pdp.app_new_site.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.app_new_site.entity.template.AbstractEntity;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends AbstractEntity {

    // COMMENTARIYANING FILEDLARI

    @Column(nullable = false,columnDefinition = "text") // TITLE UZUN BO'LISHI MUMIIN SHU U-N TEXT FORMAT BERAMIZ
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;                 // MAQOLA(POST)  Comment ichida böladi


}
