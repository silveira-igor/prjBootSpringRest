package model.dao;

import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import jakarta.xml.bind.DatatypeConverter;
import model.Usuario;

@Repository
public class DaoUsuario {

    private static final Map<String, Usuario> repositorio = new ConcurrentHashMap<>();
    //
	// BLOCO STATIC
	//
	static {
		repositorio.put("lasalle", new Usuario("lasalle",criptografarMD5("lasalle")));
		repositorio.put("alessandro", new Usuario("alessandro",criptografarMD5("chato")));
	}
	
	//
	// MÉTODOS
	//
	public DaoUsuario() {
		super();
	}
	
	public Usuario obterUsuarioPelaConta(String conta) {
		return repositorio.get(conta);
	}	
	
	
	public static String criptografarMD5(String texto) {
	    try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(texto.getBytes());
			byte[] digest = md.digest();
			return DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	    return null;
	}	
}
