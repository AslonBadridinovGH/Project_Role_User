package uz.pdp.app_new_site.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorObject {

    private   Integer     statusCode;
    private   String      message;
    private   String      type;
    private   Timestamp   timeStamp;

    private   String      resourceName;
    private   String      resourceField;
    private   Object      Object;
}
