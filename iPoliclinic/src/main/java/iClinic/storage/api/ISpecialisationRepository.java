package iClinic.storage.api;


import iClinic.model.TimeTable;
import iClinic.model.user.Specialisation;
import iClinic.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISpecialisationRepository extends JpaRepository<Specialisation, Integer> {
    Specialisation findById(int id);
}
