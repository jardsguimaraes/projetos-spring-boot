package med.voll.medvoll.dtos.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.medvoll.dtos.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
    @NotNull
    Long id,
    String nome,
    String telefone,
    DadosEndereco endereco
) {
    
}
