package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.model.Institution;
import pl.coderslab.repository.InstitutionRepository;

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
}
