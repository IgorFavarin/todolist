package br.com.cursojava.todolist.task;

import org.springframework.web.bind.annotation.RestController;

import br.com.cursojava.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/tasks")

public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;
    
    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModels taskModels, HttpServletRequest request){
        System.out.println("chegou no controller " + request.getAttribute("idUser"));
        var idUser = request.getAttribute("idUser");
        taskModels.setIdUser((UUID)idUser);

        var currentDate = LocalDateTime.now();
        if(currentDate.isAfter(taskModels.getStartAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio não pode ser menor que a atual");

        }
        if(taskModels.getStartAt().isAfter(taskModels.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio não pode ser maior que a data de termino");

        }

        var task = this.taskRepository.save(taskModels);
        return ResponseEntity.status(HttpStatus.OK).body(task);

    }
    

    @GetMapping("/")
    
    public List<TaskModels> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        var task = this.taskRepository.findByIdUser((UUID)idUser);

        return task;
    }

    //http://localhosts:8080/tasks/145225-ssdsds-dd4d4d
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModels taskModels, @PathVariable UUID id, HttpServletRequest request){
        var task = this.taskRepository.findById(id).orElse(null);
        if(task == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tarefa não existe");
        }
        
        var idUser = request.getAttribute("idUser");

        if(!task.getIdUser().equals(idUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuário não tem permissão para alterar essa task");


        }
        Utils.copyNomNullProperty(taskModels, task);

        taskModels.setIdUser((UUID) idUser);
        taskModels.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(task);


    }
}
