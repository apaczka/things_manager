package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import pl.coderslab.model.Donation;
import pl.coderslab.model.User;
import pl.coderslab.repository.DonationRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    public void saveDonation(Donation donation) {
        donationRepository.save(donation);

    }

    public void removeDonation(Long id) {
        donationRepository.delete(id);
    }

    public List<Donation> showAllDonations() {
        List<Donation> donations = new ArrayList<>();
        donations = donationRepository.findAll();
        return donations;
    }

    public List<Donation> showAllUserDonation(Long id) {
        List<Donation> donations = new ArrayList<>();
        donations = donationRepository.findByUserId(id);
        return donations;
    }

//    public List<Donation> showAllSortedDonation(User user, Sort sort) {
//        List<Donation> donations = new ArrayList<>();
//        donations = donationRepository.findByUser(user,sort);
//        return donations;
//    }

public Donation findById(Long id){
        return donationRepository.findOne(id);
}
}
