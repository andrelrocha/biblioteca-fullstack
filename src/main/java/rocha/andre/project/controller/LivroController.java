package rocha.andre.project.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocha.andre.project.domain.livro.DTO.LivroDTO;
import rocha.andre.project.domain.livro.DTO.LivroReturnDTO;
import rocha.andre.project.domain.livro.DTO.UpdateLivroDTO;
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

    @DeleteMapping("/{livroId}")
    @Transactional
    public ResponseEntity deleteLivro(@PathVariable long livroId) {
        livroService.deleteLivro(livroId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity getAllLivros() {
        var todosLivros = livroService.getAllLivros();
        return ResponseEntity.ok(todosLivros);
    }

    @GetMapping("/{livroId}")
    public ResponseEntity getLivroBtId(@PathVariable long livroId) {
        var livro = livroService.getLivroById(livroId);
        return ResponseEntity.ok(livro);
    }

    @GetMapping("/sugestao")
    public ResponseEntity sugestaoLivro() {
        var livroSugestao = livroService.sugestaoLivro();
        return ResponseEntity.ok(livroSugestao);
    }

    @PutMapping("/{livroId}")
    @Transactional
    public ResponseEntity<LivroReturnDTO> updateLivro(@RequestBody UpdateLivroDTO data, @PathVariable String livroId) {
        var livro = livroService.updateLivro(data, livroId);
        return ResponseEntity.ok(livro);
    }

}
