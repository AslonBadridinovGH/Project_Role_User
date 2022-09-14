package uz.pdp.app_new_site.payload;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostDto {

    @NotNull(message = "title bo'sh bo'lmasin")
    private String  title;

    @NotNull(message = "text bo'sh bo'lmasin")
    private String  text;

    @NotNull(message = "url bo'sh bo'lmasin")
    private String  url;
}
