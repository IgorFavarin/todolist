package br.com.cursojava.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ITaskRepository extends JpaRepository<TaskModels, UUID> {
    List<TaskModels> findByIdUser(UUID idUser);
    
}
