package uz.pdp.app_new_site.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.app_new_site.entity.Comment;
import uz.pdp.app_new_site.entity.Post;
import uz.pdp.app_new_site.entity.User;
import uz.pdp.app_new_site.exceptions.ResourceNotFoundException;
import uz.pdp.app_new_site.payload.ApiResponse;
import uz.pdp.app_new_site.payload.CommentDto;
import uz.pdp.app_new_site.repository.CommentRepository;
import uz.pdp.app_new_site.repository.PostRepository;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;


    public ApiResponse addComment(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId()).orElseThrow(
                () -> new ResourceNotFoundException("Post with id " + commentDto.getPostId()+" not found", " post", false));
        Comment comment=new Comment(commentDto.getText(),post);
        commentRepository.save(comment);
        return new ApiResponse("saved",true);
    }

    public ApiResponse editComment(Long id, CommentDto commentDto) {

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment  with id " + id, " not found", false));
        Post post = postRepository.findById(commentDto.getPostId()).orElseThrow(
                () -> new ResourceNotFoundException("Post with id " + commentDto.getPostId(), " not found", false));
        comment.setPost(post);
        comment.setText(commentDto.getText());
        commentRepository.save(comment);
        return new ApiResponse("Comment edited",true);
    }

    public ApiResponse deleteComment(Long id) {
        try {
            commentRepository.deleteById(id);
            return new ApiResponse("Comment deleted",true);
        }catch (Exception e){
            return new ApiResponse("Comment not deleted",false);
        }
    }

    public ApiResponse deleteMyComment(Long id) {

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         try {
        Comment comment = commentRepository.findByIdAndCreatedById(id, user.getId()).orElseThrow(
        () -> new ResourceNotFoundException("Comment with id " + id + "and userId " + user.getId() + " not found", " Comment", false));
        commentRepository.deleteById(comment.getId());
        return new ApiResponse("Comment deleted",true);
        }catch (Exception e){
        return new ApiResponse("Comment not deleted",false);
        }

    }
}
