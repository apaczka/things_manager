package pl.coderslab.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import pl.coderslab.validator.AddressValidator;
import pl.coderslab.validator.CategoryValidator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(groups = {CategoryValidator.class, Default.class})
    private String category;

    @Column(name="number_of_bags")
    @NotNull
    private int numberOfBags;
    @NotBlank (groups = {AddressValidator.class, Default.class})
    private String street;
    @NotBlank(groups = {AddressValidator.class, Default.class})
    private String city;
    @Column(name="postal_code")
    @NotBlank (groups = {AddressValidator.class, Default.class})
    private String postalCode;
    @Column(name="phone_number")
    @NotNull (groups = {AddressValidator.class, Default.class})
    private int phoneNumber;
    @NotNull (groups = {AddressValidator.class, Default.class})
    private LocalDate date;
    @NotNull (groups = {AddressValidator.class, Default.class})
    private LocalTime time;
    private LocalDate created;
    private boolean given;
    private String notes;
    @ManyToOne
    private User user;
    @ManyToOne
    private Institution institution;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumberOfBags() {
        return numberOfBags;
    }

    public void setNumberOfBags(int numberOfBags) {
        this.numberOfBags = numberOfBags;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public boolean isGiven() {
        return given;
    }

    public void setGiven(boolean given) {
        this.given = given;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", numberOfBags=" + numberOfBags +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", date=" + date +
                ", time=" + time +
                ", created=" + created +
                ", given=" + given +
                ", notes='" + notes + '\'' +
                ", user=" + user +
                ", institution=" + institution.getName() +
                '}';
    }
}
