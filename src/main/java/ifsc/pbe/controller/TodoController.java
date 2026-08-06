package ifsc.pbe.controller;

import ifsc.pbe.entity.Annotation;
import ifsc.pbe.entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo/v1")
public class TodoController {

    // Create a list
    @PostMapping("/lists/{list}")
    public ResponseEntity<Void> createList(@PathVariable("list") String list) {
        return null;
    }

    // All list names
    @GetMapping("/lists")
    public List<String> getListsNames() {
        return null;
    }

    // Task per List, ordered by deadline
    @GetMapping("/lists/{list}/tasks")
    public List<Task> getListTasks(@PathVariable("list") String list) {
        return null;
    }

    // An especific task
    @GetMapping("/lists/{list}/tasks/{task}")
    public Task getListTask(@PathVariable("list") String list, @PathVariable("task") String task) {
        return null;
    }

    // Add a task to a list
    @PostMapping("/lists/{list}/tasks")
    public Task addTask(@PathVariable("list") String list, @RequestBody Task task) {
        return null;
    }

    // Add annotation to a task
    @PostMapping("/lists/{list}/tasks/{task}/annotations")
    public Annotation addAnnotation(@PathVariable("list") String list, @PathVariable("task") String task, @RequestBody Annotation annotation) {
        return null;
    }

    // Move a task to a list
    @PostMapping("/lists/{list}/tasks/{task}/move/{toList}")
    public Task moveTask(@PathVariable("list") String list, @PathVariable("task") String task, @PathVariable("toList") String toList) {
        return null;
    }

    // Remove a task
    @DeleteMapping("/lists/{list}/tasks/{task}")
    public ResponseEntity<Void> removeTask(@PathVariable("list") String list, @PathVariable("task") String task) {
        return null;
    }
}