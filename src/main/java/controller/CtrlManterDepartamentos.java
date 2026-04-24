package controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Departamento;
import model.ModelException;
import model.dao.DaoDepartamento;

//
// Para os códigos do protocolo HTTP, veja https://datatracker.ietf.org/doc/html/rfc9110
//
@RestController
@RequestMapping("/depto")
public class CtrlManterDepartamentos {

    private final DaoDepartamento dao;

    public CtrlManterDepartamentos() {
        this.dao = new DaoDepartamento();
        System.out.println("CtrlManterDepartamentos");
    }

    @PostMapping("/incluir")
    public ResponseEntity<?> incluirDepartamento(@RequestBody Departamento novo) {
        Departamento salvo; 
        try {
        	salvo = dao.incluirDepartamento(novo);
        } catch(ModelException me) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(me.getMessage());
        }
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarDepartamentos() {
    	// Ordenando os departamentos pelo nome
    	Collections.sort(dao.obterDepartamentos(), (a,b) -> a.getNome().compareTo(b.getNome()));
    	// A Expressão Lambda também poderia ser: Collections.sort(dao.obterDepartamentos(), Comparator.comparing(d -> d.getNome()));
        return ResponseEntity.ok(dao.obterDepartamentos());
    }

    @GetMapping("/listar/{paramId}")
    public ResponseEntity<?> listarDepartamento(@PathVariable("paramId") int id) {
        Departamento depto = dao.obterDepartamento(id);
        if (depto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Departamento não encontrado");
        }
        return ResponseEntity.ok(depto);
    }

    @PutMapping("/alterar/{paramId}")
    public ResponseEntity<?> alterarDepartamento(@PathVariable("paramId") int id, @RequestBody Departamento alterar) {
        Departamento depto = dao.obterDepartamento(id);
        if (depto == null) 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Departamento não encontrado");
        try {
            depto.setSigla(alterar.getSigla());
            depto.setNome(alterar.getNome());
        } catch(ModelException me) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(me.getMessage());
        }
        Departamento atualizado = dao.alterarDepartamento(depto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/remover/{paramId}")
    public ResponseEntity<?> removerDepartamento(@PathVariable("paramId") int id) {
        Departamento depto = dao.obterDepartamento(id);
        if (depto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Departamento não encontrado");
        }
        dao.removerDepartamento(depto);
        return ResponseEntity.ok(depto);
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<String> tratarErro(Exception ex) {
        Throwable causa = ex.getCause();

        while (causa != null) {
            if (causa instanceof ModelException) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(causa.getMessage());
            }
            causa = causa.getCause();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados invalidos na requisicao.");
    }

}