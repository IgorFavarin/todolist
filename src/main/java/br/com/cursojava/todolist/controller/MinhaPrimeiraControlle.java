package br.com.cursojava.todolist.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/primeiraRota")

//http://localhost:8080...

public class MinhaPrimeiraControlle {
    /**
     * metodos de uma requisição http
     * get - buscar info
     * post - add um dado/info
     * put - alterar um dado/info
     * delete - remover um dado/info
     * path - alterar somente uma parte da info/dado
     */

     @GetMapping("/primeiroMetodo")     

    public String primeiraMensagem(){
        return "funcionou";
    }
    
}
