package iClinic.storage.api;


import iClinic.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface IMedicalHistoryRepository extends JpaRepository<User, Integer> {

}
