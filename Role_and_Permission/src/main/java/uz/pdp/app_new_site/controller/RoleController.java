package uz.pdp.app_new_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_new_site.aop.CheckPermission;
import uz.pdp.app_new_site.entity.Role;
import uz.pdp.app_new_site.payload.ApiResponse;
import uz.pdp.app_new_site.payload.RoleDto;
import uz.pdp.app_new_site.repository.RoleRepository;
import uz.pdp.app_new_site.service.RoleService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;


    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping
    public HttpEntity<?>addRole(@Valid @RequestBody RoleDto roleDto){
        ApiResponse apiResponse= roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    // @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @CheckPermission(permission = "VIEW_ROLES")
    @GetMapping("/roles")
        public HttpEntity<?>getRoles(){
        List<RoleDto> roleDtos =new ArrayList<>();
        for (Role role : roleRepository.findAll()) {
            roleDtos.add(new RoleDto(role.getName(), role.getDescription(), role.getPermissionList()));
        }
     return ResponseEntity.ok().body(roleDtos);
    }


    // @PreAuthorize(value = "hasAuthority('EDIT_ROLE')")
    @CheckPermission(permission = "EDIT_ROLE")
    @PutMapping("/{id}")
    public HttpEntity<?>editRole(@PathVariable Long id, @Valid @RequestBody RoleDto roleDto){
        ApiResponse apiResponse= roleService.editRole(id, roleDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    // @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @CheckPermission(permission = "DELETE_ROLE")
    @DeleteMapping("/deleteRole/{id}")
    public HttpEntity<?>deleteRole(@PathVariable Long id){
        ApiResponse apiResponse= roleService.deleteRole(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
