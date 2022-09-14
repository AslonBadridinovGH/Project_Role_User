package uz.pdp.app_new_site.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_new_site.entity.Lavozim;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

   private String message;

   private boolean success;
}
