package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.Usuario;
import model.dao.DaoUsuario;

@RestController
@RequestMapping("/auth")
public class CtrlAutenticacao {

    private final DaoUsuario dao;

    public CtrlAutenticacao() {
        this.dao = new DaoUsuario();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario login, HttpServletRequest req) {
        String conta = login.getConta();
        String senhaMD5 = login.getSenhaMD5().toUpperCase();
        Usuario usr = dao.obterUsuarioPelaConta(conta); 

        if (usr == null) 
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Conta não autorizada:'" + conta + "'");

        if (!usr.getSenhaMD5().equals(senhaMD5)) 
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha inválida para a conta '" + conta + "'");

        // Esse exemplo utiliza a abordagem STATEFUL para identificar que o usuário está autenticado
        HttpSession session = req.getSession(true);
        session.setAttribute("conta", conta);
        return ResponseEntity.ok("Login da conta '" + conta + "' feito com sucesso!");
    }
}