package rocha.andre.project.service;

import rocha.andre.project.domain.emprestimo.DTO.EmprestimoDTO;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoListagemDTO;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoReturnDTO;

import java.util.ArrayList;
import java.util.List;

public interface EmprestimoService {
    EmprestimoReturnDTO emprestimoLivro(EmprestimoDTO data, String tokenJWT);
    ArrayList<EmprestimoListagemDTO> listaEmprestimos(String tokenJWT);
}
