package pl.coderslab.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.coderslab.model.Donation;
import pl.coderslab.model.User;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findByUserId(Long id);
//    List<Donation> findDonationByGiven();
//    List<Donation> findDonationByGivenAndUserId(Long id);
    List<Donation> findByUserOrderByDateDescCreatedDesc(User user);

}
