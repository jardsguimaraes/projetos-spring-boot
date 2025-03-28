package med.voll.medvoll.dtos.medico;

import med.voll.medvoll.enumeracoes.Especialidade;
import med.voll.medvoll.model.Medico;

public record DadosListagemMedicos(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {
    public DadosListagemMedicos(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
