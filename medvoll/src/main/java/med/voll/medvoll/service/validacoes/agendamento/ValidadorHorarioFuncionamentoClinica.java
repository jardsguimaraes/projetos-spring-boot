package med.voll.medvoll.service.validacoes.agendamento;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.medvoll.dtos.agendamento.DadosAgendamentoConsulta;
import med.voll.medvoll.infra.exception.ValidacaoException;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsultas {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var clinicaFechada = dataConsulta.getHour() < 7 || dataConsulta.getHour() > 18;
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        if (domingo || clinicaFechada) {
            throw new ValidacaoException("Consulta não pode ser agendada para " +
                "domingo ou fora do horário de funcionamento da clínica");
        }
    }
}