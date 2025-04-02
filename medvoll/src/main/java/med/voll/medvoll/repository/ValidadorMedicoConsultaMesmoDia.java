package med.voll.medvoll.repository;

import med.voll.medvoll.dtos.agendamento.DadosAgendamentoConsulta;
import med.voll.medvoll.infra.exception.ValidacaoException;

public class ValidadorMedicoConsultaMesmoDia {

    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var medicoPossuiConsultaMesmoDia = consultaRepository
                .existsByMedicoIdAndDataAndMotivoIsNull(dados.idMedico(), dados.data());

        if (medicoPossuiConsultaMesmoDia) {
            throw new ValidacaoException("Médico já possui uma consulta agendada para o mesmo dia");
        }
    }
}
