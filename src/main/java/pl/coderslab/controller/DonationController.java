package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Donation;
import pl.coderslab.model.Institution;
import pl.coderslab.model.User;
import pl.coderslab.repository.DonationRepository;
import pl.coderslab.service.DonationService;
import pl.coderslab.service.InstitutionService;
import pl.coderslab.validator.AddressValidator;
import pl.coderslab.validator.CategoryValidator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes({"page1", "page2", "page3", "page4", "page5", "edt1", "edt2", "edt3", "edt4", "edt5",})
@RequestMapping("/donation")
public class DonationController {

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private DonationService donationService;

    @Autowired
    private HttpSession session;
    @Autowired
    private DonationRepository donationRepository;

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
    public String addpage1(@Validated({CategoryValidator.class}) Donation donation, BindingResult result,Model model) {
if(result.hasErrors()){
    return"donation/addpage1";
}
        model.addAttribute("page1", donation);
        model.addAttribute("donation", new Donation());
        return "donation/addpage2";


    }

    @RequestMapping("/page1/edit")
    public String editpage1(Model model, HttpSession session) {
        Donation donation = (Donation) session.getAttribute("page1");
        model.addAttribute("donation", donation);
        return "donation/addpage1";

    }

    @GetMapping("/page1/edt/{id}")
    public String edtPage1(@PathVariable Long id, Model model) {
        Donation donation = donationService.findById(id);
        model.addAttribute("donation", donation);
        return "donation/edtpage1";

    }

    @PostMapping("/page1/edt")
    public String edtPage1(@ModelAttribute Donation donation, Model model) {
        model.addAttribute("edt1", donation);
        Donation donation1 = donationService.findById(donation.getId());
        model.addAttribute("donation", donation1);
        return "donation/edtpage2";
    }

    @RequestMapping("/page1/edt/edt")
    public String prevEdtpage1(Model model, HttpSession session) {
        Donation donation = (Donation) session.getAttribute("edt1");
        model.addAttribute("donation", donation);
        return "donation/edtpage1";
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
    public String editpage2(Model model, HttpSession session) {
        Donation donation = (Donation) session.getAttribute("page2");
        model.addAttribute("donation", donation);
        return "donation/addpage2";

    }
    @RequestMapping("/page2/again")
    public String showPage2(Model model){
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
    @RequestMapping("/page2/again/edt")
    public String showPage2Edt(Model model){
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
        return "donation/edtpage3";


    }

    @RequestMapping("/page2/edt")
    public String edtpage2(@ModelAttribute Donation donation, Model model) {
        model.addAttribute("edt2", donation);
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
        return "donation/edtpage3";
    }

    @RequestMapping("/page2/edt/edt")
    public String edtEdtpage2(Model model, HttpSession session) {
        Donation donation = (Donation) session.getAttribute("edt2");
        model.addAttribute("donation", donation);
        return "donation/edtpage2";

    }

    @PostMapping("/page3")
    public String addpage3(@ModelAttribute Institution institution, Model model) {
        List<Institution> institutions = new ArrayList<>();
        model.addAttribute("page3", institution);
        String name = institution.getName();
        String description = institution.getDescription();
        String location = institution.getLocation();
        if (!(name.equals(null) || name.equals(""))) {
            institutions = institutionService.searchByLocationAndDescAndName(location, description, name);
            if (!(institutions.isEmpty())) {
                model.addAttribute("institutions", institutions);
            }
            return "redirect:/donation/page2";
        } else {
            institutions = institutionService.searchByLocationAndDesc(location, description);
            if (institutions.isEmpty()) {
                return "redirect:/donation/page2";
            }
            model.addAttribute("institutions", institutions);
        }
        model.addAttribute("donation", new Donation());

        return "donation/addpage4";
    }

    @PostMapping("page3/edt")
    public String edtpage3(@ModelAttribute Institution institution, Model model) {
        List<Institution> institutions = new ArrayList<>();
        model.addAttribute("edt3", institution);
        String name = institution.getName();
        String description = institution.getDescription();
        String location = institution.getLocation();
        if (!(name.equals(null) || name.equals(""))) {
            institutions = institutionService.searchByLocationAndDescAndName(location, description, name);
            if (!(institutions.isEmpty())) {
                model.addAttribute("institutions", institutions);
            }
            return "redirect:/donation/page2/edt";
        } else {
            institutions = institutionService.searchByLocationAndDesc(location, description);
            if (institutions.isEmpty()) {
                return "redirect:/donation/page2/edt";
            }
            model.addAttribute("institutions", institutions);
        }
        model.addAttribute("donation", new Donation());
        return "donation/edtpage4";
    }

    @PostMapping("/page4")
    public String addpage4(@ModelAttribute Institution institution, Model model) {
        model.addAttribute("page4", institution);
        model.addAttribute("donation", new Donation());
        return "donation/addpage5";

    }




    @PostMapping("/page5")
    public String addpage5(@Validated({AddressValidator.class}) Donation donation, BindingResult result, Model model) {
if(result.hasErrors()){
    return "donation/addpage5";
}
        model.addAttribute("page5", donation);

        return "donation/addpage6";

    }

    @RequestMapping("/page5/edit")
    public String editpage5(Model model, HttpSession session) {
        Donation donation = (Donation) session.getAttribute("page5");
        model.addAttribute("donation", donation);
        return "donation/addpage5";

    }

    @PostMapping("/page5/edt")
    public String edtpage5(@ModelAttribute Donation donation, Model model) {

        model.addAttribute("edt5", donation);
        return "donation/edtpage6";
    }

    @RequestMapping("/page5/edt/edt")
    public String editEdtpage5(Model model, HttpSession session) {
        Donation donation = (Donation) session.getAttribute("edt5");
        model.addAttribute("donation", donation);
        return "donation/edtpage5";

    }

    @RequestMapping("/page6")
    public String saveDonation(HttpSession session) {

        Donation result = (Donation) session.getAttribute("page5");
        Donation page1 = (Donation) session.getAttribute("page1");
        Donation page2 = (Donation) session.getAttribute("page2");
        User user = (User) session.getAttribute("user");
        Institution page4 = (Institution) session.getAttribute("page4");
        result.setCategory(page1.getCategory());
        result.setNumberOfBags((page2.getNumberOfBags()));
        result.setInstitution(page4);
        result.setUser(user);
        result.setCreated(LocalDate.now());

        donationService.saveDonation(result);
        session.removeAttribute("page1");
        session.removeAttribute("page2");
        session.removeAttribute("page3");
        session.removeAttribute("page4");
        session.removeAttribute("page5");

        return "donation/addpage7";
    }

    @RequestMapping("/page6/edt")
    public String saveEdtDonation(HttpSession session) {

        Donation result = (Donation) session.getAttribute("edt5");
        Donation page1 = (Donation) session.getAttribute("edt1");
        Donation page2 = (Donation) session.getAttribute("edt2");
        User user = (User) session.getAttribute("user");
        Institution page4 = (Institution) session.getAttribute("edt4");
        result.setCategory(page1.getCategory());
        result.setNumberOfBags((page2.getNumberOfBags()));
        result.setInstitution(page4);
        result.setUser(user);
        result.setCreated(LocalDate.now());

        donationService.saveDonation(result);
        session.removeAttribute("edt1");
        session.removeAttribute("edt2");
        session.removeAttribute("edt3");
        session.removeAttribute("edt4");
        session.removeAttribute("edt5");

        return "donation/addpage7";
    }

    @RequestMapping("/all")
    public String showAllDonationsSorted(User user, Model model) {
        User user1 = (User) session.getAttribute("user");
        List<Donation> donations = donationRepository.findByUserOrderByDateDescCreatedDesc(user1);
        model.addAttribute("donations", donations);
        return "donation/all";


    }

    @RequestMapping("/remove/{id}")
    public String removeDonation(@PathVariable Long id) {
        donationService.removeDonation(id);
        return "redirect:/donation/all";
    }

    @RequestMapping("/mark/{id}")
    public String markAsGiven(@PathVariable Long id) {
        Donation donation = donationService.findById(id);
        donation.setGiven(true);
        donationService.saveDonation(donation);
        return "redirect:/donation/all";
    }
}

