package med.voll.medvoll.dtos.agendamento;

import java.time.LocalDateTime;

import med.voll.medvoll.enumeracoes.MotivoCancelamento;
import med.voll.medvoll.model.Consulta;

public record DadosDetalhamentoConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data,
        boolean confirmada,
        MotivoCancelamento motivo) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(),
            consulta.getPaciente().getId(), consulta.getData(),
            consulta.isConfirmada(), consulta.getMotivo());
    }

}
