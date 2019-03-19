package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Donation;
import pl.coderslab.model.Institution;
import pl.coderslab.model.User;
import pl.coderslab.service.DonationService;
import pl.coderslab.service.InstitutionService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes({"page1", "page2","page3","page4","page5"})
@RequestMapping("/donation")
public class DonationController {

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private DonationService donationService;

//    @Autowired
//    private User user;

    @ModelAttribute("categories")
    List<String> showCategories() {
        String[] category = {"ubrania, które nadają się do ponownego użycia", "ubrania, do wyrzucenia", "zabawki", "książki", "inne"};
        List<String> categories = Arrays.asList(category);
        return categories;
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("donation", new Donation());
        return "donation/addpage1";
    }

    @PostMapping("/page1")
    public String addpage1(@ModelAttribute Donation donation, Model model) {
        model.addAttribute("page1", donation);
        model.addAttribute("donation", new Donation());
        return "donation/addpage2";


    }
    @RequestMapping("/page1/edit")
    public String editpage1(Model model, HttpSession session){
        Donation donation = (Donation)session.getAttribute("page1");
        model.addAttribute("donation", donation);
        return "donation/addpage1";

    }
    @RequestMapping("/page2")
    public String addpage2(@ModelAttribute Donation donation, Model model) {
        model.addAttribute("page2", donation);
        List<Institution> institutions = institutionService.showAllInstitutions();
        List<String> locations = new ArrayList<>();
        List<String> helps = new ArrayList<>();
        for (Institution institution : institutions) {
            locations.add(institution.getLocation());
            helps.add(institution.getDescription());
        }

        model.addAttribute("locations", locations);
        model.addAttribute("helps", helps);
        model.addAttribute("institution", new Institution());
        return "donation/addpage3";

    }
    @RequestMapping("/page2/edit")
    public String editpage2(Model model, HttpSession session){
        Donation donation = (Donation)session.getAttribute("page2");
        model.addAttribute("donation", donation);
        return "donation/addpage2";

    }

    @PostMapping("/page3")
    public String addpage3(@ModelAttribute Institution institution, Model model) {
        List<Institution> institutions = new ArrayList<>();
        model.addAttribute("page3", institution);
        String name = institution.getName();
        String description = institution.getDescription();
        String location = institution.getLocation();

        if (!name.equals(null)) {
            institutions.add(institutionService.searchByname(name));
            model.addAttribute("institutions", institutions);
        }
        institutions = institutionService.searchByLocationAndDesc(location, description);
        model.addAttribute("institutions", institutions);
        model.addAttribute("donation", new Donation());
        return "donation/addpage4";
    }
//
//    @RequestMapping("/page3/edit")
//    public String editpage3(Model model){
//        List<Institution> institutions = institutionService.showAllInstitutions();
//        List<String> locations = new ArrayList<>();
//        List<String> helps = new ArrayList<>();
//        for (Institution institution : institutions) {
//            locations.add(institution.getLocation());
//            helps.add(institution.getDescription());
//        }
//
//        model.addAttribute("locations", locations);
//        model.addAttribute("helps", helps);
//        model.addAttribute("institution", new Institution());
//        return "donation/addpage3";
//
//    }

    @PostMapping("/page4")
    public String addpage4(@ModelAttribute Institution institution, Model model) {
        model.addAttribute("page4", institution);
        model.addAttribute("donation", new Donation());
        return "donation/addpage5";

    }

    @RequestMapping("/page4/edit")
    public String editpage4(Model model, HttpSession session){
        Institution institution = (Institution)session.getAttribute("page4");
        Donation donation = new Donation();
        donation.setInstitution(institution);
        model.addAttribute("donation", donation);
        return "donation/addpage4";

    }
    @PostMapping("/page5")
    public String addpage5(@ModelAttribute Donation donation, Model model) {
        model.addAttribute("page5", donation);

        return "donation/addpage6";

    }

    @RequestMapping("/page5/edit")
    public String editpage5(Model model, HttpSession session){
        Donation donation = (Donation)session.getAttribute("page5");
        model.addAttribute("donation", donation);
        return "donation/addpage5";

    }
    @RequestMapping("/page6")
    public String saveDonation(HttpSession session) {

        Donation result = (Donation) session.getAttribute("page5");
        Donation page1 = (Donation) session.getAttribute("page1");
        Donation page2 = (Donation) session.getAttribute("page2");
        Institution page4 = (Institution) session.getAttribute("page4");
        result.setCategory(page1.getCategory());
        result.setNumberOfBags((page2.getNumberOfBags()));
        result.setInstitution(page4);
//        result.setUser(user);
        result.setCreated(LocalDate.now());

        donationService.saveDonation(result);
        session.removeAttribute("page1");
        session.removeAttribute("page2");
        session.removeAttribute("page3");
        session.removeAttribute("page4");
        session.removeAttribute("page5");

        return "donation/addpage7";
    }

}

