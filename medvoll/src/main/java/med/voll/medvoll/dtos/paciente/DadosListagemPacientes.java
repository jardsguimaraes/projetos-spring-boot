package med.voll.medvoll.dtos.paciente;

import med.voll.medvoll.model.Paciente;

public record DadosListagemPacientes(
    Long id,
    String nome,
    String email,
    String cpf
) {
    public DadosListagemPacientes(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
