package rocha.andre.project.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoDTO;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoListagemDTO;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoReturnDTO;
import rocha.andre.project.service.EmprestimoService;

import java.util.ArrayList;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {
    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping("/{tokenJWT}")
    @Transactional
    public ResponseEntity<EmprestimoReturnDTO> realizarEmprestimo(@PathVariable String tokenJWT, @RequestBody EmprestimoDTO data) {
        var emprestimo = emprestimoService.emprestimoLivro(data, tokenJWT);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(emprestimo);
    }

    @GetMapping("/{tokenJWT}")
    @Transactional
    public ResponseEntity<ArrayList<EmprestimoListagemDTO>> listarEmprestimos(@PathVariable String tokenJWT) {
        var emprestimo = emprestimoService.listaEmprestimos(tokenJWT);
        return ResponseEntity.ok(emprestimo);
    }

    @GetMapping("/calcular/{tokenJWT}")
    @Transactional
    public ResponseEntity calcularValorTotal(@PathVariable String tokenJWT) {
        var emprestimo = emprestimoService.GetValorTotalEmprestimo(tokenJWT);
        return ResponseEntity.ok(emprestimo);
    }
}
