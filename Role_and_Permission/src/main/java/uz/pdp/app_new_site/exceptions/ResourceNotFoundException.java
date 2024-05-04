package uz.pdp.app_new_site.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@Setter
@ToString
public class ResourceNotFoundException extends RuntimeException{


     private  String resourceName;
     private  String resourceField;
     private  Object Object;

        public ResourceNotFoundException(String resourceName, String resourceField, java.lang.Object object) {
            this.resourceName = resourceName;
            this.resourceField = resourceField;
            Object = object;
        }
    }
