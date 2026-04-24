package model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import model.Departamento;
import model.ModelException;

@Repository
public class DaoDepartamento {

    private static final Map<Integer, Departamento> banco = new ConcurrentHashMap<>();
    private int sequence = 1;

    public DaoDepartamento() {
    	System.out.println("DaoDepartamento instanciado");
    }
    
    public Departamento incluirDepartamento(Departamento novo) throws ModelException {
        novo.setId(sequence++);
        banco.put(novo.getId(), novo);
        return novo;
    }

    public List<Departamento> obterDepartamentos() {
        return new ArrayList<>(banco.values());
    }

    public Departamento obterDepartamento(int id) {
        return banco.get(id);
    }

    public Departamento alterarDepartamento(Departamento depto) {
        banco.put(depto.getId(), depto);
        return depto;
    }

    public Departamento removerDepartamento(Departamento depto) {
        return banco.remove(depto.getId());
    }
}