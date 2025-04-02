package med.voll.medvoll.dtos.agendamento;

import java.time.LocalDateTime;

import med.voll.medvoll.enumeracoes.EspecialidadeMedica;
import med.voll.medvoll.model.Consulta;

public record DadosListagemConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        String nomeMedico,
        String nomePaciente,
        EspecialidadeMedica especialidadeMedica,
        LocalDateTime dataConsulta) {
    public DadosListagemConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), 
            consulta.getPaciente().getId(), consulta.getMedico().getNome(), 
            consulta.getPaciente().getNome(), consulta.getMedico().getEspecialidade(),
            consulta.getData());
    }
}
