package ifsc.pbe.service;

import ifsc.pbe.entity.Annotation;
import ifsc.pbe.entity.Task;
import ifsc.pbe.entity.TaskList;
import ifsc.pbe.repository.AnnotationRepository;
import ifsc.pbe.repository.TaskListRepository;
import ifsc.pbe.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        if (taskRepository.existsById(task.getName())) {
            throw new IllegalArgumentException("Tarefa já existe: " + task.getName());
        }
        TaskList list = findListOrThrow(listName);
        task.setList(list);
        task.setDateTime(LocalDateTime.now());
        return taskRepository.save(task);
    }
    // Add an annotation to a task
    public Annotation addAnnotation(String listName,String taskName, Annotation annotation) {
        Task task = findTaskOrThrow(listName,taskName);
        annotation.setId(null);
        annotation.setDateTime(LocalDateTime.now());
        annotation.setTask(task);
        return annotationRepository.save(annotation);
    }
    //Remove a task
    public void removeTask(String listName,String taskName) {
        Task task = findTaskOrThrow(listName,taskName);
        taskRepository.delete(task);
    }

    // Move a Task to another list
    public Task moveTask(String listName,String taskName, String toListName) {
        Task task = findTaskOrThrow(listName,taskName);
        TaskList toList = findListOrThrow(toListName);
        task.setList(toList);
        task.setDateTime(LocalDateTime.now());
        return taskRepository.save(task);
    }
    // All list names
    public List<String> getListNames() {
        return taskListRepository.findAll()
                .stream()
                .map(TaskList::getName)
                .toList();
    }

    // Task per List, ordered by deadline
    public List<Task> getListTasks(String listName) {
        findListOrThrow(listName);
        return taskRepository.findByList_NameOrderByDelTime(listName);
    }

    // An especific task
    public Task getTask(String listName,String taskName) {
        return findTaskOrThrow(listName,taskName);
    }

    private Task findTaskOrThrow(String listName, String taskName) {
        Task task = taskRepository.findById(taskName)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada: " + taskName));
        if (!task.getList().getName().equals(listName)) {
            throw new IllegalArgumentException("Tarefa não pertence à lista: " + listName);
        }
        return task;
    }

    private TaskList findListOrThrow(String listName) {
        return taskListRepository.findById(listName)
                .orElseThrow(() -> new IllegalArgumentException("Lista não encontrada: " + listName));
    }

}
