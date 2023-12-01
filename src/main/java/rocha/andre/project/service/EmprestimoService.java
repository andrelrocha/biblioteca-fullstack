package rocha.andre.project.service;

import rocha.andre.project.domain.emprestimo.DTO.*;

import java.util.ArrayList;
import java.util.List;

public interface EmprestimoService {
    EmprestimoReturnDTO emprestimoLivro(EmprestimoDTO data, String tokenJWT);
    ArrayList<EmprestimoListagemDTO> listaEmprestimos(String tokenJWT);
    double getValorTotalEmprestimo(String tokenJWT);
    ArrayList<EmprestimosLivroDTO> getAllEmprestimosByLivroId(Long livroId);
    void retornarLivro(EmprestimoRetornarDTO data, String tokenJWT);
}
