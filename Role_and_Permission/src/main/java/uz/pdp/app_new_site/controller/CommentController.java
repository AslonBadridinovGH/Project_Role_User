package uz.pdp.app_new_site.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_new_site.aop.CheckPermission;
import uz.pdp.app_new_site.payload.ApiResponse;
import uz.pdp.app_new_site.payload.CommentDto;
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




    // @PreAuthorize(value = "hasAuthority('ADD_COMMENT')")
    @CheckPermission(permission = "ADD_COMMENT")
    @PostMapping
    public HttpEntity<?>addComment(@Valid @RequestBody CommentDto commentDto){
        ApiResponse apiResponse=commentService.addComment(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    // @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @CheckPermission(permission = "EDIT_COMMENT")
    @PutMapping("/{id}")
    public HttpEntity<?>editComment(@PathVariable Long id, @Valid @RequestBody CommentDto commentDto){
        ApiResponse apiResponse=commentService.editComment(id,commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    // @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @CheckPermission(permission = "DELETE_COMMENT")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?>deleteComment(@PathVariable Long id){
        ApiResponse apiResponse=commentService.deleteComment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    // @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @CheckPermission(permission = "DELETE_MY_COMMENT")
    @DeleteMapping("/deleteMyComment/{id}")
    public HttpEntity<?>deleteMyComment(@PathVariable Long id){
        ApiResponse apiResponse=commentService.deleteMyComment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
