package rocha.andre.project.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoDTO;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoReturnDTO;
import rocha.andre.project.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {
    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    @Transactional
    public ResponseEntity<EmprestimoReturnDTO> realizarEmprestimo(@RequestBody EmprestimoDTO data) {
        var emprestimo = emprestimoService.emprestimoLivro(data);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(emprestimo);
    }
}
