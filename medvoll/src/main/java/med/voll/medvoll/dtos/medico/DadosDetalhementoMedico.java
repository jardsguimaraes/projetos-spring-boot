package med.voll.medvoll.dtos.medico;

import med.voll.medvoll.enumeracoes.Especialidade;
import med.voll.medvoll.model.Endereco;
import med.voll.medvoll.model.Medico;

public record DadosDetalhementoMedico(
    Long id,
    String nome,
    String crm,
    Especialidade especialidade,
    String telefone,
    String email,
    Endereco endereco
) {
    public DadosDetalhementoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEspecialidade(), medico.getTelefone(), medico.getEmail(), medico.getEndereco());
    }
}
