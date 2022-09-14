package uz.pdp.app_new_site.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_new_site.aop.HuquqniTekshirish;
import uz.pdp.app_new_site.payload.ApiResponse;
import uz.pdp.app_new_site.payload.CommentDto;
import uz.pdp.app_new_site.payload.PostDto;
import uz.pdp.app_new_site.service.CommentService;
import uz.pdp.app_new_site.service.PostService;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;



     // ADMIN QILA OLADI
    //@PreAuthorize(value = "hasAuthority('ADD_COMMENT')") // TIZIM GA KIRGAN USERDA ADD_COMMENT HUQUQI BORLIGINI TEKSHIRADI
    @HuquqniTekshirish(huquq = "ADD_COMMENT")
    @PostMapping
    public HttpEntity<?>addComment(@Valid @RequestBody CommentDto commentDto){  // @NotNull ishlashi u-n, @Valid anotatsizasi kerak
        ApiResponse apiResponse=commentService.addComment(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    // @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @HuquqniTekshirish(huquq = "EDIT_COMMENT")
    @PutMapping("/{id}")                     // @NotNull ishlashi u-n, @Valid anotatsizasi kerak
    public HttpEntity<?>editComment(@PathVariable Long id, @Valid @RequestBody CommentDto commentDto){
        ApiResponse apiResponse=commentService.editComment(id,commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    // @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @HuquqniTekshirish(huquq = "DELETE_COMMENT")
    @DeleteMapping("/delete/{id}")                     // @NotNull ishlashi u-n, @Valid anotatsizasi kerak
    public HttpEntity<?>deleteComment(@PathVariable Long id){
        ApiResponse apiResponse=commentService.deleteComment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    // @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @HuquqniTekshirish(huquq = "DELETE_MY_COMMENT")
    @DeleteMapping("/deleteMyComment/{id}")                     // @NotNull ishlashi u-n, @Valid anotatsizasi kerak
    public HttpEntity<?>deleteMyComment(@PathVariable Long id){
        ApiResponse apiResponse=commentService.deleteMyComment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
