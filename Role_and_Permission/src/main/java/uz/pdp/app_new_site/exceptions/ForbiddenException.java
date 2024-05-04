package uz.pdp.app_new_site.exceptions;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.FORBIDDEN)
@Getter
@Setter
@ToString
public class ForbiddenException  extends RuntimeException{

     private String type;
     private String message;

    public ForbiddenException(String type, String message) {

        this.type =  type;
        this.message = message;
    }

}
