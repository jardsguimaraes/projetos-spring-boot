package med.voll.medvoll.dtos.agendamento;

import jakarta.validation.constraints.NotNull;
import med.voll.medvoll.enumeracoes.MotivoCancelamento;

public record DadosCancelamentoConsulta(
                @NotNull Long idConsulta,
                @NotNull MotivoCancelamento motivo
// @NotNull @Future @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime data
) {
}
