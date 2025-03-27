package med.voll.medvoll.dtos.paciente;

import med.voll.medvoll.model.PacienteModel;

public record DadosListagemPacientes(
    Long id,
    String nome,
    String email,
    String cpf
) {
    public DadosListagemPacientes(PacienteModel paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
