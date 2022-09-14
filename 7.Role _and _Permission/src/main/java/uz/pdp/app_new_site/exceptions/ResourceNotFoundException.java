package uz.pdp.app_new_site.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

                                             // AuthService dagiregisterUser da chaqirdik
                                             // EXCEPTION QAYTARADIGAN METHOD.
@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@Setter
@ToString
public class ResourceNotFoundException extends RuntimeException{

     // NIMA BO'YICHA QIDIRDIB TOPA OLMAGANIMIZ !!! :
     private  String resourceName;    // lavozim
     private  String resourceField;   // name
     private  Object Object;          // qaysi FIELD qidirdik: USER, ADMIN, 1, 500

        public ResourceNotFoundException(String resourceName, String resourceField, java.lang.Object object) {
            this.resourceName = resourceName;
            this.resourceField = resourceField;
            Object = object;
        }
    }
