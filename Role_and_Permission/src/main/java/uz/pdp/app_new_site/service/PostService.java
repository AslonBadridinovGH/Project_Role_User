package uz.pdp.app_new_site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_new_site.entity.Post;
import uz.pdp.app_new_site.exceptions.ResourceNotFoundException;
import uz.pdp.app_new_site.payload.ApiResponse;
import uz.pdp.app_new_site.payload.PostDto;
import uz.pdp.app_new_site.repository.PostRepository;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;


    public ApiResponse addPost(PostDto postDto) {
        Post post= new Post(postDto.getTitle(), postDto.getText(), postDto.getUrl());
        postRepository.save(post);
        return new ApiResponse("saved",true);
    }

    public ApiResponse editPost(Long id, PostDto postDto) {

        Optional<Post> byId = postRepository.findById(id);
        if (!byId.isPresent()){
            throw new ResourceNotFoundException("Post with id "+id," not found",false);
        }
         Post post = byId.get();
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setUrl(postDto.getUrl());
        postRepository.save(post);
        return new ApiResponse("edited",true);
    }

    public ApiResponse deletePost(Long id) {
        try {
            postRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }catch (Exception e){
            // return new ApiResponse("not deleted",false);
            throw  new ResourceNotFoundException("Post with Id: "+id,"not deleted", false);
        }

    }
}
