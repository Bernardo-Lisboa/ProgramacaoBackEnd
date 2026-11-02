package ifsc.pbe.repository;

import ifsc.pbe.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByList_NameOrderByDelTime(String listName);
}