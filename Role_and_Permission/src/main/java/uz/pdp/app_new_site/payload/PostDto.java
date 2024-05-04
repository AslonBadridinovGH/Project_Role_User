package uz.pdp.app_new_site.payload;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostDto {

    @NotNull(message = "title  should not be empty")
    private String  title;

    @NotNull(message = "text  should not be empty")
    private String  text;

    @NotNull(message = "url should not be empty")
    private String  url;
}
