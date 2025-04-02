package med.voll.medvoll.dtos.agendamento;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.medvoll.enumeracoes.EspecialidadeMedica;

public record DadosAgendamentoConsulta(
                Long id,
                @NotNull Long idMedico,
                @NotNull Long idPaciente,
                @NotNull @Future @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime data,
                EspecialidadeMedica especialidade) {
}
