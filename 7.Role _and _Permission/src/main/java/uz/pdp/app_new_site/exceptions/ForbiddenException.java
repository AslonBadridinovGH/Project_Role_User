package uz.pdp.app_new_site.exceptions;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

                                        // CheckPermissionExcecutor Classida chaqirdik
@ResponseStatus(HttpStatus.FORBIDDEN)  // Error number :  403
@Getter
@Setter
@ToString
public class ForbiddenException  extends RuntimeException{

     private String type;       // NIMANI TEKSHIRGAN EDIK: XATOLIK TURI
     private String message;

    public ForbiddenException(String type, String message) {

        this.type =  type;
        this.message = message;
    }

}
