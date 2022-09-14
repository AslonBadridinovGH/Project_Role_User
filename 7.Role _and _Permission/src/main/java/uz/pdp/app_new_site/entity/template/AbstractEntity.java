package uz.pdp.app_new_site.entity.template;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.app_new_site.entity.User;
import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass     // Classs ni Super Class sifatida ko'rsin
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {

            // MANASHU 5 TA CLASS HAMMA CLASSLARDA BÖLADI SHU UCHUN ABSTRAC CLASS QILIB OLDIK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp  // Object yaratildan vaqtni avtomatik oladi
    private Timestamp createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    // SecurityContexholderda kim bölib , AbstractEntity dan voris olgan
    // Class ni kim yaratgan bölsa shu yerga Id si chiqadi
    @JoinColumn(updatable = false)       // User Classni özimiz yaratganimiz uchun Column emas JoinColumn böladi
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)   // Bir komentni 2-3 kishi yozishi mumkin
    private User createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;
}
