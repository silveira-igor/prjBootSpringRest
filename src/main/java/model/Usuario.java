package model;

import java.io.Serializable;

public class Usuario implements Serializable {
	//
	// ATRIBUTOS
	//
	private String conta;
	private String senhaMD5;

	//
	// MÉTODOS
	//
	public Usuario() {		
		super();
	}
	
	public Usuario(String conta, String senhaMD5) {
		super();
		this.conta = conta;
		this.senhaMD5 = senhaMD5;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getSenhaMD5() {
		return senhaMD5;
	}

	public void setSenhaMD5(String senhaMD5) {
		this.senhaMD5 = senhaMD5;
	}
		
	@Override
	public String toString() {
		return "Usuario [conta=" + conta + ", senhaMD5=" + senhaMD5 +  "]";
	}
}
