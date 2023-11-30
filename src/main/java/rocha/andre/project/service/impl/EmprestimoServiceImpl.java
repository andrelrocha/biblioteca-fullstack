package rocha.andre.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoDTO;
import rocha.andre.project.domain.emprestimo.DTO.EmprestimoReturnDTO;
import rocha.andre.project.domain.emprestimo.useCase.EmprestimoUseCase;
import rocha.andre.project.service.EmprestimoService;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {
    @Autowired
    private EmprestimoUseCase emprestimoUseCase;


    @Override
    public EmprestimoReturnDTO emprestimoLivro(EmprestimoDTO data) {
        var emprestimo = emprestimoUseCase.emprestimoLivro(data);
        return emprestimo;
    }
}
