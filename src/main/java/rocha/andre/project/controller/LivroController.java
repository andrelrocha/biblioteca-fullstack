package rocha.andre.project.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocha.andre.project.domain.livro.DTO.LivroDTO;
import rocha.andre.project.domain.livro.DTO.LivroReturnDTO;
import rocha.andre.project.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<LivroReturnDTO> createLivro(@RequestBody LivroDTO data) {
        var livro = livroService.createLivro(data);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(livro);
    }


    @GetMapping("/sugestao")
    public ResponseEntity sugestaoLivro() {
        var livroSugestao = livroService.sugestaoLivro();
        return ResponseEntity.ok(livroSugestao);
    }

}
