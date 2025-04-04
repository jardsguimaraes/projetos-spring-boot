package med.voll.medvoll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.medvoll.dtos.agendamento.DadosAgendamentoConsulta;
import med.voll.medvoll.dtos.agendamento.DadosCancelamentoConsulta;
import med.voll.medvoll.dtos.agendamento.DadosDetalhamentoConsulta;
import med.voll.medvoll.infra.exception.ValidacaoException;
import med.voll.medvoll.model.Consulta;
import med.voll.medvoll.model.Medico;
import med.voll.medvoll.repository.ConsultaRepository;
import med.voll.medvoll.repository.MedicoRepository;
import med.voll.medvoll.repository.PacienteRepository;
import med.voll.medvoll.service.validacoes.agendamento.ValidadorAgendamentoConsultas;
import med.voll.medvoll.service.validacoes.cancelamento.ValidadorCancelamentoConsultas;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsultas> validadoresAgendamentos;

    @Autowired
    private List<ValidadorCancelamentoConsultas> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Paciente não encontrado");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Médico não encontrado");
        }

        validadoresAgendamentos.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data");

        }
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, dados.data(), true, null);

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatório quando o médico não for informado");
        }

        return medicoRepository.escolherMedicoAleatorioLivreData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Consulta não encontrada");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
