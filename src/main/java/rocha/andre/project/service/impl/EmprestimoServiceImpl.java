package rocha.andre.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocha.andre.project.domain.emprestimo.DTO.*;
import rocha.andre.project.domain.emprestimo.useCase.*;
import rocha.andre.project.service.EmprestimoService;

import java.util.ArrayList;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {
    @Autowired
    private EmprestimoUseCase emprestimoUseCase;
    @Autowired
    private ListaEmprestimosUseCase listaEmprestimosUseCase;
    @Autowired
    private ListaEmprestimosLivroUseCase listaEmprestimosLivroUseCase;
    @Autowired
    private RetornarLivroUseCase retornarLivroUseCase;
    @Autowired
    private SomaValoresEmprestimosUseCase somaValoresEmprestimosUseCase;


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

    @Override
    public double getValorTotalEmprestimo(String tokenJWT) {
        var valor = somaValoresEmprestimosUseCase.GetValorTotalEmprestimo(tokenJWT);
        return valor;
    }

    @Override
    public ArrayList<EmprestimosLivroDTO> getAllEmprestimosByLivroId(Long livroId) {
        var emprestimos = listaEmprestimosLivroUseCase.getAllEmprestimosByLivroId(livroId);
        return emprestimos;
    }

    @Override
    public void retornarLivro(EmprestimoRetornarDTO data, String tokenJWT) {
        retornarLivroUseCase.retornarLivro(data, tokenJWT);
    }
}
