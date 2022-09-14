package uz.pdp.app_new_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_new_site.entity.Comment;


import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Optional<Comment> findByIdAndCreatedById(Long id, Long createdBy_id);

}
