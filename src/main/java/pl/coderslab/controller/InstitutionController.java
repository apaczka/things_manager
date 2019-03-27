package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

    @ModelAttribute("helps")
    List<String> showCategories() {
        String[] category = {"dzieci", "bezdomni", "samotne matki", "osoby starsze", "inne"};
        List<String> categories = Arrays.asList(category);
        return categories;
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/add")
    public String addInstitutionForm(Model model){
        model.addAttribute("institution", new Institution());
        return "institution/add";
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/add")
    public String addInstitution(@Valid Institution institution, BindingResult result){
        if(result.hasErrors()) {
            return "institution/add";
        }
           institutionService.saveInstitution(institution);
        return "redirect:/institution/all";
    }

    @RequestMapping("/all")
    public String showAllInstitution(Model model){
        List<Institution> institutions = new ArrayList<>();
        institutions=institutionService.showAllInstitutions();
        model.addAttribute("institutions", institutions);
        return "institution/all";
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping("/remove/{id}")
    public String removeInsttution(@PathVariable Long id){
        institutionService.removeInstitution(id);
        return "redirect:/institution/all";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/edit/{id}")
    public String editInstitutionForm(@PathVariable Long id, Model model){
        Institution institution = institutionService.findById(id);
        model.addAttribute("institution", institution);
        return "institution/edit";
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/edit")
    public String editInsitution(@Valid Institution institution, BindingResult result){
        if(result.hasErrors()){
            return "institution/edit";
        }institutionService.saveInstitution(institution);
        return "redirect:/institution/all";
    }

}
