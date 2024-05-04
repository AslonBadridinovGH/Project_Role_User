package uz.pdp.app_new_site.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.pdp.app_new_site.entity.ErrorObject;

import java.sql.Timestamp;


@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResponseEntity<ErrorObject>handleForbiddenException(ForbiddenException ex){

         ErrorObject errorObject=new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setType(ex.getType());
        errorObject.setStatusCode(HttpStatus.FORBIDDEN.value());
        errorObject.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorObject, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorObject>handleResourceNotFoundException(ResourceNotFoundException ex){

          ErrorObject errorObject=new ErrorObject();
        errorObject.setResourceName(ex.getResourceField());
        errorObject.setResourceField(ex.getResourceField());
        errorObject.setObject(ex.getObject());

        errorObject.setMessage(ex.getResourceName());
        errorObject.setType(ex.getResourceField());
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setTimeStamp(new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);

    }

}
