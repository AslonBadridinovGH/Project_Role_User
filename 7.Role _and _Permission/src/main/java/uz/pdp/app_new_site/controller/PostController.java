package uz.pdp.app_new_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_new_site.aop.HuquqniTekshirish;
import uz.pdp.app_new_site.payload.ApiResponse;
import uz.pdp.app_new_site.payload.LavozimDto;
import uz.pdp.app_new_site.payload.PostDto;
import uz.pdp.app_new_site.service.LavozimService;
import uz.pdp.app_new_site.service.PostService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;


     // ADMIN QILA OLADI
    @PreAuthorize(value = "hasAuthority('ADD_POST')") // TIZIM GA KIRGAN USERDA ADD_POST HUQUQI BORLIGINI TEKSHIRADI
    @PostMapping
    public HttpEntity<?>addPost(@Valid @RequestBody PostDto postDto){  // @NotNull ishlashi u-n, @Valid anotatsizasi kerak
        ApiResponse apiResponse=postService.addPost(postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    // @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @HuquqniTekshirish(huquq = "EDIT_POST")
    @PutMapping("/{id}")                     // @NotNull ishlashi u-n, @Valid anotatsizasi kerak
    public HttpEntity<?>editPost(@PathVariable Long id, @Valid @RequestBody PostDto postDto){
        ApiResponse apiResponse=postService.editPost(id,postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    // @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @HuquqniTekshirish(huquq = "DELETE_POST")
    @DeleteMapping("/{id}")
    public HttpEntity<?>deletePost(@PathVariable Long id){
        ApiResponse apiResponse=postService.deletePost(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


}
