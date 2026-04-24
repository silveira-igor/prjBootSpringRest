package model;

import java.io.Serializable;

public class Departamento implements Serializable, Comparable<Departamento> {
	//
	// ATRIBUTOS
	//
	private int id;
	private String sigla;
	private String nome;

	//
	// METODOS
	//
	public Departamento() {
	}

	public Departamento(int id, String s, String n) throws ModelException {
		super();
		this.setId(id);
		this.setSigla(s);
		this.setNome(n);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) throws ModelException {
		Departamento.validarId(id);
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String s) throws ModelException {
		Departamento.validarSigla(s);
		this.sigla = s;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String n) throws ModelException {
		Departamento.validarNome(n);
		this.nome = n;
	}

	@Override
	public String toString() {
		return nome;
	}

	public int compareTo(Departamento outro) {
		return this.nome.compareTo(outro.nome);
	}

	public static void validarId(int id) throws ModelException {
		if (id <= 0) {
			throw new ModelException("O ID do departamento e invalido: " + id);
		}
	}

	public static void validarSigla(String sigla) throws ModelException {
		if (sigla == null || sigla.length() != 2) {
			throw new ModelException(
				"A sigla e invalida. Informe exatamente 2 caracteres, por exemplo: FI, RH ou TI. Valor recebido: "
					+ sigla);
		}
	}

	public static void validarNome(String nome) throws ModelException {
		if (nome == null || nome.length() > 40) {
			throw new ModelException("O nome informado e invalido. Use ate 40 caracteres. Valor recebido: " + nome);
		}
	}
}
