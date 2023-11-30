package rocha.andre.project.service;

import rocha.andre.project.domain.emprestimo.DTO.EmprestimoDTO;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoReturnDTO;

import java.util.List;

public interface EmprestimoService {
    EmprestimoReturnDTO emprestimoLivro(EmprestimoDTO data, String tokenJWT);
    List<EmprestimoReturnDTO> listaEmprestimos(String tokenJWT);
}
