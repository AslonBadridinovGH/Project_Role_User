package uz.pdp.app_new_site.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_new_site.entity.Role;
import uz.pdp.app_new_site.exceptions.ResourceNotFoundException;
import uz.pdp.app_new_site.payload.ApiResponse;
import uz.pdp.app_new_site.payload.RoleDto;
import uz.pdp.app_new_site.repository.RoleRepository;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addRole(RoleDto roleDto) {

        if(roleRepository.existsByName(roleDto.getName()))
         return new ApiResponse("such role is already exist",false);

        Role role =new Role(roleDto.getName(), roleDto.getPermissionList(), roleDto.getDescription());
        roleRepository.save(role);
        return new ApiResponse("saved",true);
    }

    public ApiResponse editRole(Long id, RoleDto roleDto) {

        if(!roleRepository.findById(id).isPresent())
            return new ApiResponse("such role is not exist",false);

        if(roleRepository.existsByName(roleDto.getName()))
            return new ApiResponse("such role is already exist",false);

        Optional<Role> optionalRole = roleRepository.findById(id);
        if (!optionalRole.isPresent()) {
            throw  new ResourceNotFoundException("Role", "with id", id + " not found");
        }

        Role role = optionalRole.get();
        new Role( roleDto.getName(), roleDto.getPermissionList(), roleDto.getDescription());

        roleRepository.save(role);
        return new ApiResponse("EDITED",true);
    }


    public ApiResponse deleteRole(Long id) {
        try {
           roleRepository.deleteById(id);
           return new ApiResponse("deleted",true);
        }catch (Exception e){
          // return new ApiResponse("not deleted",false);
           throw  new ResourceNotFoundException("role with Id: "+id,"not deleted", false);
        }
    }
}
