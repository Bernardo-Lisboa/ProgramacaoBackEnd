package ifsc.pbe.repository;

import ifsc.pbe.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList, String> {
}