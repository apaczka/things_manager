package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Institution;
import pl.coderslab.service.InstitutionService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/institution")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;


    @GetMapping("/add")
    public String addInstitutionForm(Model model){
        model.addAttribute("institution", new Institution());
        return "institution/add";
    }
    @PostMapping("/add")
    public String addInstitution(@Valid Institution institution, BindingResult result){
        if(result.hasErrors()) {
            return "institution/add";
        }
           institutionService.saveInstitution(institution);
        return "institution/all";
    }
    @RequestMapping("/all")
    public String showAllInstitution(Model model){
        List<Institution> institutions = new ArrayList<>();
        institutions=institutionService.showAllInstitutions();
        model.addAttribute("institutions", institutions);
        return "institution/all";
    }
    @RequestMapping("/remove/{id}")
    public String removeInsttution(@PathVariable Long id){
        institutionService.removeInstitution(id);
        return "institution/all";
    }
    @GetMapping("/edit/{id}")
    public String editInstitutionForm(@PathVariable Long id, Model model){
        Institution institution = institutionService.findById(id);
        model.addAttribute("institution", institution);
        return "institution/edit";
    }
    @PostMapping("/edit")
    public String editInsitution(@Valid Institution institution, BindingResult result){
        if(result.hasErrors()){
            return "institution/edit";
        }institutionService.saveInstitution(institution);
        return "institution/all";
    }

}
