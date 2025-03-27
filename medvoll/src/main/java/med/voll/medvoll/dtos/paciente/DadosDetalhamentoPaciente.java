package med.voll.medvoll.dtos.paciente;

import med.voll.medvoll.model.Endereco;
import med.voll.medvoll.model.Paciente;

public record DadosDetalhamentoPaciente(
    Long id,
    String nome,
    String cpf,
    String telefone,
    String email,
    Endereco endereco
) {
    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getTelefone(), paciente.getEmail(), paciente.getEndereco());
    }
}
