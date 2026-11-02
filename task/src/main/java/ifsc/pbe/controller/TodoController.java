package ifsc.pbe.controller;

import ifsc.pbe.entity.Annotation;
import ifsc.pbe.entity.Task;
import ifsc.pbe.entity.TaskList;
import ifsc.pbe.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo/v1")
public class TodoController {

    private final TaskService taskService;

    public TodoController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Create a list
    @PostMapping("/lists/{list}")
    public ResponseEntity<TaskList> createList(@PathVariable("list") String list) {
        TaskList created = taskService.createTaskList(list);
        return ResponseEntity.ok(created);
    }

    // All list names
    @GetMapping("/lists")
    public List<String> getListsNames() {
        return taskService.getListNames();
    }

    // Task per List, ordered by deadline
    @GetMapping("/lists/{list}/tasks")
    public List<Task> getListTasks(@PathVariable("list") String list) {
        return taskService.getListTasks(list);
    }

    // An especific task
    @GetMapping("/lists/{list}/tasks/{task}")
    public Task getListTask(@PathVariable("list") String list, @PathVariable("task") String task) {
        return taskService.getTask(list, task);
    }

    // Add a task to a list
    @PostMapping("/lists/{list}/tasks")
    public Task addTask(@PathVariable("list") String list, @RequestBody Task task) {
        return taskService.addTask(list, task);
    }

    // Add annotation to a task
    @PostMapping("/lists/{list}/tasks/{task}/annotations")
    public Annotation addAnnotation(@PathVariable("list") String list, @PathVariable("task") String task, @RequestBody Annotation annotation) {
        return taskService.addAnnotation(list, task, annotation);
    }

    // Move a task to a list
    @PostMapping("/lists/{list}/tasks/{task}/move/{toList}")
    public Task moveTask(@PathVariable("list") String list, @PathVariable("task") String task, @PathVariable("toList") String toList) {
        return taskService.moveTask(list, task, toList);
    }

    // Remove a task
    @DeleteMapping("/lists/{list}/tasks/{task}")
    public ResponseEntity<Void> removeTask(@PathVariable("list") String list, @PathVariable("task") String task) {
        taskService.removeTask(list, task);
        return ResponseEntity.noContent().build();
    }
}