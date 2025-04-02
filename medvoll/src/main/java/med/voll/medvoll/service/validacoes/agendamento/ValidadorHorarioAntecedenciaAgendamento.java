package med.voll.medvoll.service.validacoes.agendamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.medvoll.dtos.agendamento.DadosAgendamentoConsulta;
import med.voll.medvoll.infra.exception.ValidacaoException;

@Component
public class ValidadorHorarioAntecedenciaAgendamento implements ValidadorAgendamentoConsultas {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var antecedencia = Duration.between(agora, dataConsulta).toMinutes();

        if (antecedencia < 30) {
            throw new ValidacaoException("A Consulta deve ser agendada " +
                    "com antecedência mínima de 30 minutos");
        }
    }
}