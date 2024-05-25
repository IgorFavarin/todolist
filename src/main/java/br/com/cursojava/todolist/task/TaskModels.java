package br.com.cursojava.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
/**
 * ID
 * usuario (id_usuário)
 * descrição
 * titulo
 * data de inicio
 * data de termino
 * prioridade
 */
@Data
@Entity(name="tb_tasks")
public class TaskModels {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private UUID idUser;
    private String description;

    @Column(length = 50)
    private String tittle;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private int priority;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTittle(String tittle) throws Exception {
        if(tittle.length() > 50){
            throw new Exception("o campo tittle deve conter no maximo 50 caracteres");
        }
        this.tittle = tittle;

    }

}