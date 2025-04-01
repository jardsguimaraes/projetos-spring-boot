package med.voll.medvoll.service.validacoes.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.medvoll.dtos.agendamento.DadosAgendamentoConsulta;
import med.voll.medvoll.infra.exception.ValidacaoException;
import med.voll.medvoll.repository.ConsultaRepository;
import med.voll.medvoll.service.validacoes.agendamento.ValidadorAgendamentoConsultas;

@Component
public class ValidadorPacienteSemOutraConsultaDia implements ValidadorAgendamentoConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(),
                primeiroHorario, ultimoHorario);

        if (pacientePossuiConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada para o mesmo dia");

        }
    }
}
