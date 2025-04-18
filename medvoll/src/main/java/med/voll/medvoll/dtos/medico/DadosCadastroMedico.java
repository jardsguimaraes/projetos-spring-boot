package med.voll.medvoll.dtos.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.medvoll.dtos.endereco.DadosEndereco;
import med.voll.medvoll.enumeracoes.EspecialidadeMedica;

public record DadosCadastroMedico(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank
        String telefone,
        @NotBlank 
        @Pattern(regexp = "\\d{4,6}", message = "{crm.invalido}")
        String crm,
        @NotNull
        EspecialidadeMedica especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco) {

}
