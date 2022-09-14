package uz.pdp.app_new_site.aop;
import java.lang.annotation.*;
// @PreAuthorize(value = "hasAuthority('ADD_LAVOZIM')")  ANOTATSIYASI O'RNIDA O'ZIMIZ HuquqniTekshirish ANNOTATSIYASINI YARATAMIZ
@Documented
@Target(ElementType.METHOD)          //   QAZERDA ISHLASHI KERAKLIGI
@Retention(RetentionPolicy.RUNTIME)  //   QACHON ISHLASHI KERAKLIGI
public @interface HuquqniTekshirish {

      String huquq();   // HuquqniTekshirish ANNOTATSIYASING VALUE SI MASALAN:  value = "hasAuthority('EDIT_LAVOZIM')"
}
