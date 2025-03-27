package med.voll.medvoll.dtos.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.medvoll.dtos.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
    @NotNull
    Long id,
    String nome,
    String telefone,
    DadosEndereco endereco
) {
    
}
