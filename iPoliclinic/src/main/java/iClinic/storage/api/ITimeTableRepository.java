package iClinic.storage.api;


import iClinic.model.TimeTable;
import iClinic.model.user.Specialisation;
import iClinic.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ITimeTableRepository extends JpaRepository<TimeTable, Integer> {
    List<TimeTable> findAllTimeTableByDoctorIn(List<User> listUser);
    TimeTable findById(int id);
}
