package ifsc.pbe.controller;

import ifsc.pbe.entity.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo/v1")
public class TodoController {
    @GetMapping("/lists")
    public List<String> getListsNames(){
        return null;
    }

    @GetMapping("/lists/{list}")
    public List<Task> getListTasks(@PathVariable("list") String list){
        return null;
    }
    @GetMapping("/lists/{list}/{task}")
    public Task getListTask(@PathVariable("list") String list,@PathVariable("task") String task){
        return null;
    }

    @PostMapping("/lists/{list}/{task}/addAnot/{anot}")
    public String setTaskAnot(@PathVariable("list") String list,@PathVariable("task") String task,@PathVariable("anot") String anot){
        return null;
    }

    @PostMapping("/lists/{list}/add/{task}")
    public Task setListTask(@PathVariable("list") String list,@PathVariable("task") String task){
        return null;
    }
    @PostMapping("/lists/{list}/move/{task}")
    public Task setTaskToList(@PathVariable("list") String list,@PathVariable("task") String task){
        return null;
    }
    @PostMapping("/lists/{list}/remove/{task}")
    public Task removeTask(@PathVariable("list") String list,@PathVariable("task") String task){
        return null;
    }
}
