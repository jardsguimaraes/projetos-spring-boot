package med.voll.medvoll.service.validacoes.cancelamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.medvoll.dtos.agendamento.DadosCancelamentoConsulta;
import med.voll.medvoll.infra.exception.ValidacaoException;
import med.voll.medvoll.repository.ConsultaRepository;

@Component
public class ValidadorHorarioAntecedenciaCancelamento implements ValidadorCancelamentoConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var agora = LocalDateTime.now();
        var dataConsulta = consultaRepository.getReferenceById(dados.idConsulta()).getData();
        var antecedencia = Duration.between(agora, dataConsulta).toHours();

        if (antecedencia < 24) {
            throw new ValidacaoException("A consulta deve ser cancelada com " +
                "antecedência mínima de 24 horas");
        }
    }
}
