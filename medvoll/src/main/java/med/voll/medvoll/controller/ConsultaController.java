package med.voll.medvoll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.medvoll.dtos.agendamento.DadosAgendamentoConsulta;
import med.voll.medvoll.dtos.agendamento.DadosCancelamentoConsulta;
import med.voll.medvoll.dtos.agendamento.DadosDetalhamentoConsulta;
import med.voll.medvoll.dtos.agendamento.DadosListagemConsulta;
import med.voll.medvoll.repository.ConsultaRepository;
import med.voll.medvoll.service.AgendaDeConsultaService;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService agenda;

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> cadastrar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemConsulta>> listar(
            @PageableDefault(size = 10, sort = { "id" }) Pageable paginacao) {
        var page = consultaRepository.findByConfirmadaTrue(paginacao).map(DadosListagemConsulta::new);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
