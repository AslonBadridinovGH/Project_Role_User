package uz.pdp.app_new_site.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_new_site.entity.Comment;
import uz.pdp.app_new_site.entity.Post;
public interface PostRepository extends JpaRepository<Post, Long> {
}
