package ifsc.pbe.service;

import ifsc.pbe.entity.Task;
import ifsc.pbe.entity.TaskList;
import ifsc.pbe.repository.AnnotationRepository;
import ifsc.pbe.repository.TaskListRepository;
import ifsc.pbe.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;
    private final AnnotationRepository annotationRepository;

    //Initializing Repos
    public TaskService(TaskRepository taskRepository,
                       TaskListRepository taskListRepository,
                       AnnotationRepository annotationRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
        this.annotationRepository = annotationRepository;
    }

    //Create a Task List
    public TaskList createTaskList(String listName) {
        if (taskListRepository.existsById(listName)) {
            throw new IllegalArgumentException("Lista já existe: " + listName);
        }
        TaskList list = new TaskList();
        list.setName(listName);
        return taskListRepository.save(list);
    }

    //Add a task to an existing list
    public Task addTask(String listName, Task task) {
        TaskList list = findListOrThrow(listName);
        task.setList(list);
        task.setDateTime(LocalDateTime.now());
        return taskRepository.save(task);
    }
    //Remove a Task
    public void removeTask(String taskName) {
        Task task = findTaskOrThrow(taskName);
        taskRepository.delete(task);
    }

    // Move a Task to another list
    public Task moveTask(String taskName, String toListName) {
        Task task = findTaskOrThrow(taskName);
        TaskList toList = findListOrThrow(toListName);
        task.setList(toList);
        task.setDateTime(LocalDateTime.now());
        return taskRepository.save(task);
    }

    private Task findTaskOrThrow(String taskName) {
        return taskRepository.findById(taskName)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada: " + taskName));
    }

    private TaskList findListOrThrow(String listName) {
        return taskListRepository.findById(listName)
                .orElseThrow(() -> new IllegalArgumentException("Lista não encontrada: " + listName));
    }

}
