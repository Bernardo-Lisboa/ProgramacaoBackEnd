package ifsc.pbe.controller;

import ifsc.pbe.entity.Annotation;
import ifsc.pbe.entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo/v1")
public class TodoController {

    // Cria uma lista (identificada por nome)
    @PostMapping("/lists/{list}")
    public ResponseEntity<Void> createList(@PathVariable("list") String list) {
        return null;
    }

    // Nomes de todas as listas
    @GetMapping("/lists")
    public List<String> getListsNames() {
        return null;
    }

    // Tarefas de uma lista, ordenadas por prazo
    @GetMapping("/lists/{list}/tasks")
    public List<Task> getListTasks(@PathVariable("list") String list) {
        return null;
    }

    // Uma tarefa específica
    @GetMapping("/lists/{list}/tasks/{task}")
    public Task getListTask(@PathVariable("list") String list, @PathVariable("task") String task) {
        return null;
    }

    // Cadastra tarefa numa lista
    @PostMapping("/lists/{list}/tasks")
    public Task addTask(@PathVariable("list") String list, @RequestBody Task task) {
        return null;
    }

    // Acrescenta anotação a uma tarefa
    @PostMapping("/lists/{list}/tasks/{task}/annotations")
    public Annotation addAnnotation(@PathVariable("list") String list, @PathVariable("task") String task, @RequestBody Annotation annotation) {
        return null;
    }

    // Move tarefa de uma lista para outra
    @PostMapping("/lists/{list}/tasks/{task}/move/{toList}")
    public Task moveTask(@PathVariable("list") String list, @PathVariable("task") String task, @PathVariable("toList") String toList) {
        return null;
    }

    // Remove tarefa
    @DeleteMapping("/lists/{list}/tasks/{task}")
    public ResponseEntity<Void> removeTask(@PathVariable("list") String list, @PathVariable("task") String task) {
        return null;
    }
}