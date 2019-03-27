package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.model.Institution;
import pl.coderslab.repository.InstitutionRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class InstitutionService {

    @Autowired
   private InstitutionRepository institutionRepository;

    public void saveInstitution(Institution institution){
        institutionRepository.save(institution);
    }

    public void removeInstitution(Long id){
        institutionRepository.delete(id);
    }

    public List<Institution> showAllInstitutions(){
        List<Institution> institutions = new ArrayList<>();
        institutions =institutionRepository.findAll();
        return institutions;
    }
    public Institution findById(Long id){
        return institutionRepository.findOne(id);
    }
    public List<Institution> searchByLocationAndDesc(String location, String desc){
         List<Institution> institutions=institutionRepository.findByLocationAndDescription(location, desc);
return institutions;
}
public List<Institution>searchByLocationAndDescAndName(String location, String desc, String name){
         List<Institution> institutions = institutionRepository.findByLocationOrDescriptionOrName(location, desc, name);
             return institutions;
}

public Institution searchByname(String name){
       return institutionRepository.findByName(name);
}
public List<Institution> searchByLocation(String location){
        List<Institution> institutions = institutionRepository.findByLocation(location);
        return institutions;
}
public List<Institution> searchByDescription(String desc) {
    List<Institution> institutions = institutionRepository.findByDescription(desc);
    return institutions;
}
    public List<Institution> searchByNameAndDescription(String name, String desc) {
        List<Institution> institutions = institutionRepository.findByNameAndDescription(name, desc);
        return institutions;
    }
    public List<Institution> searchByNameAndLocation(String name, String location) {
        List<Institution> institutions = institutionRepository.findByNameAndLocation(name, location);
        return institutions;
    }
}

