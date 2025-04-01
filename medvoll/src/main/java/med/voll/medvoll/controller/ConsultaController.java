package med.voll.medvoll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.medvoll.dtos.agendamento.DadosAgendamentoConsulta;
import med.voll.medvoll.dtos.agendamento.DadosDetalhamentoConsulta;
import med.voll.medvoll.service.AgendaDeConsultaService;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService agenda;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> cadastrar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar() {
        return null;
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar() {
        return null;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> cancelar() {
        return null;
    }
}
