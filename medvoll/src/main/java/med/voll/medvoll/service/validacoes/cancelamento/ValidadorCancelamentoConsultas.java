package med.voll.medvoll.service.validacoes.cancelamento;

import med.voll.medvoll.dtos.agendamento.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoConsultas {

    void validar(DadosCancelamentoConsulta dados);
}
