package med.voll.medvoll.service.validacoes.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.medvoll.dtos.agendamento.DadosAgendamentoConsulta;
import med.voll.medvoll.infra.exception.ValidacaoException;
import med.voll.medvoll.repository.PacienteRepository;
import med.voll.medvoll.service.validacoes.agendamento.ValidadorAgendamentoConsultas;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsultas {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());

        if (!pacienteAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");

        }
    }
}
