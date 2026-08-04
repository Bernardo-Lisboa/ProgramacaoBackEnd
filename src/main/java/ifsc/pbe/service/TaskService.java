package ifsc.pbe.service;

import ifsc.pbe.entity.Task;
import ifsc.pbe.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class TaskService {
    TaskRepository taskRepository;

    public TaskService(){
    }
    public void createTask(String name, String desc, LocalTime delTime, LocalDateTime dateTime,String anot){
        Task task = new Task();
        task.setName(name);
        task.setDesc(desc);
        task.setDelTime(delTime);
        task.setDateTime(dateTime);
        task.setAnot(anot);
        taskRepository.save(task);
    }

}
