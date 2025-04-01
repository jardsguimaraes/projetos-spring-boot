package med.voll.medvoll.dtos.agendamento;

import java.time.LocalDateTime;

import med.voll.medvoll.model.Consulta;

public record DadosDetalhamentoConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), 
                consulta.getPaciente().getId(), consulta.getData());
    }

}
