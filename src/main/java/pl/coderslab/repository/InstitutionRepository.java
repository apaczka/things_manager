package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.Institution;

import java.util.List;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

List<Institution>findByLocationAndDescription(String location, String description);
List<Institution>findByLocationOrDescriptionOrName(String location, String description, String name);
Institution findByName(String name);
List<Institution> findByLocation(String location);
List<Institution> findByDescription(String description);
List<Institution> findByNameAndLocation(String name, String description);
List<Institution> findByNameAndDescription(String name, String description);

}
