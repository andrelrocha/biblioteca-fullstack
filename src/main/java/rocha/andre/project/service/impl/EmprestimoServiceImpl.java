package rocha.andre.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoDTO;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoListagemDTO;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoReturnDTO;
import rocha.andre.project.domain.emprestimo.useCase.EmprestimoUseCase;
import rocha.andre.project.domain.emprestimo.useCase.ListaEmprestimosUseCase;
import rocha.andre.project.service.EmprestimoService;

import java.util.ArrayList;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {
    @Autowired
    private EmprestimoUseCase emprestimoUseCase;
    @Autowired
    private ListaEmprestimosUseCase listaEmprestimosUseCase;


    @Override
    public EmprestimoReturnDTO emprestimoLivro(EmprestimoDTO data, String tokenJWT) {
        var emprestimo = emprestimoUseCase.emprestimoLivro(data, tokenJWT);
        return emprestimo;
    }

    @Override
    public ArrayList<EmprestimoListagemDTO> listaEmprestimos(String tokenJWT) {
        var emprestimos = listaEmprestimosUseCase.listaEmprestimos(tokenJWT);
        return emprestimos;
    }
}
