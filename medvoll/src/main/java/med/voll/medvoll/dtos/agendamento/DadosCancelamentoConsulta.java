package med.voll.medvoll.dtos.agendamento;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.medvoll.enumeracoes.MotivoCancelamento;

public record DadosCancelamentoConsulta(
        @NotNull Long idConsulta,
        @NotNull MotivoCancelamento motivo
        // @NotNull @Future @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime data
        ) {
}
