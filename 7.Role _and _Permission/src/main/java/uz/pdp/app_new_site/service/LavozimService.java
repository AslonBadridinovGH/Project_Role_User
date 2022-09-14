package uz.pdp.app_new_site.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_new_site.entity.Lavozim;
import uz.pdp.app_new_site.exceptions.ResourceNotFoundException;
import uz.pdp.app_new_site.payload.ApiResponse;
import uz.pdp.app_new_site.payload.LavozimDto;
import uz.pdp.app_new_site.repository.LavozimRepository;
import java.util.Optional;

@Service
public class LavozimService {

    @Autowired
    LavozimRepository lavozimRepository;

    public ApiResponse addLavozim(LavozimDto lavozimDto) {

        if(lavozimRepository.existsByName(lavozimDto.getName()))
         return new ApiResponse("Bunday lavoim bor",false);

        Lavozim lavozim=new Lavozim(lavozimDto.getName(), lavozimDto.getHuquqList(), lavozimDto.getDescription());
        lavozimRepository.save(lavozim);
        return new ApiResponse("Saqlandi",true);
    }

    public ApiResponse editLavozim(Long id, LavozimDto lavozimDto) {

        if(!lavozimRepository.findById(id).isPresent())
            return new ApiResponse("Bunday lavoim yo'q",false);

        if(lavozimRepository.existsByName(lavozimDto.getName()))
            return new ApiResponse("Bunday lavoim bor",false);

        Optional<Lavozim> optionalLavozim = lavozimRepository.findById(id);
        if (!optionalLavozim.isPresent())
        throw  new ResourceNotFoundException("Lavozim", "with id", id + " not found");

        Lavozim lavozim = optionalLavozim.get();
        new Lavozim( lavozimDto.getName(), lavozimDto.getHuquqList(),lavozimDto.getDescription());

        lavozimRepository.save(lavozim);
        return new ApiResponse("EDITED",true);
    }


    public ApiResponse deleteLavozim(Long id) {
        try {
           lavozimRepository.deleteById(id);
           return new ApiResponse("deleted",true);
        }catch (Exception e){
          // return new ApiResponse("not deleted",false);
           throw  new ResourceNotFoundException("Lavozim with Id: "+id,"not deleted", false);
        }
    }
}
