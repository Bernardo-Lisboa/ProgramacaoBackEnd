package ifsc.pbe.service;

import ifsc.pbe.entity.Task;
import ifsc.pbe.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    TaskRepository taskRepository;

    public TaskService(){
    }
    public void createTask(String name){
        Task task = new Task();


        taskRepository.save(task);
    }

}
