package uz.pdp.app_new_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_new_site.aop.HuquqniTekshirish;
import uz.pdp.app_new_site.entity.Lavozim;
import uz.pdp.app_new_site.payload.ApiResponse;
import uz.pdp.app_new_site.payload.LavozimDto;
import uz.pdp.app_new_site.payload.UserDto;
import uz.pdp.app_new_site.repository.LavozimRepository;
import uz.pdp.app_new_site.service.LavozimService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/lavozim")
public class LavozimController {

    @Autowired
    LavozimService lavozimService;

    @Autowired
    LavozimRepository lavozimRepository;


    // AADMIN QILA OLADI
    @PreAuthorize(value = "hasAuthority('ADD_LAVOZIM')") // TIZIM GA KIRGAN USERDA ADD_LAVOZIM HUQUQI BORLIGINI TEKSHIRADI
    @PostMapping                      // @NotNull ishlashi u-n, @Valid anotatsizasi kerak
    public HttpEntity<?>addLavozim(@Valid @RequestBody LavozimDto lavozimDto){
        ApiResponse apiResponse=lavozimService.addLavozim(lavozimDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    // @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @HuquqniTekshirish(huquq = "VIEW_LAVOZIMLAR")
    @GetMapping("/lavozimlar")
    public HttpEntity<?>getLavozimlar(){
        List<LavozimDto>lavozimDtos=new ArrayList<>();
        for (Lavozim lavozim : lavozimRepository.findAll()) {
            lavozimDtos.add(new LavozimDto(lavozim.getName(),lavozim.getDescription(),lavozim.getHuquqlarList()));
        }
     return ResponseEntity.ok().body(lavozimDtos);
    }


    // @PreAuthorize(value = "hasAuthority('EDIT_LAVOZIM')")
    @HuquqniTekshirish(huquq = "EDIT_LAVOZIM")
    @PutMapping("/{id}")                     // @NotNull ishlashi u-n, @Valid anotatsizasi kerak
    public HttpEntity<?>editLavozim(@PathVariable Long id, @Valid @RequestBody LavozimDto lavozimDto){
        ApiResponse apiResponse=lavozimService.editLavozim(id,lavozimDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    // @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @HuquqniTekshirish(huquq = "DELETE_LAVOZIM")
    @DeleteMapping("/deleteLavozim/{id}")
    public HttpEntity<?>deleteLavozim(@PathVariable Long id){
        ApiResponse apiResponse=lavozimService.deleteLavozim(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
