package uz.pdp.app_new_site.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommentDto {

    @NotNull(message = "text bo'sh bo'lmasin")
    private String text;

    @NotNull(message = "postId bo'sh bo'lmasin")
    private Long   postId;                         // MAQOLA(POST)  Comment ichida böladi
}
