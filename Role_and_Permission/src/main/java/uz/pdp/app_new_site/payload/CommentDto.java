package uz.pdp.app_new_site.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommentDto {

    @NotNull(message = "text should not be empty")
    private String text;

    @NotNull(message = "postId  should not be empty")
    private Long   postId;

}
