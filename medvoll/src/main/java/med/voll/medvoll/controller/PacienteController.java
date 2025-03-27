package med.voll.medvoll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;

import jakarta.validation.Valid;
import med.voll.medvoll.dtos.paciente.DadosAtualizacaoPaciente;
import med.voll.medvoll.dtos.paciente.DadosCadastroPaciente;
import med.voll.medvoll.dtos.paciente.DadosListagemPacientes;
import med.voll.medvoll.model.PacienteModel;
import med.voll.medvoll.repository.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
        repository.save(new PacienteModel(dados));
    }

    @GetMapping
    public Page<DadosListagemPacientes> lisar(@PageableDefault(size = 10, sort = { "nome" }, direction = Sort.Direction.ASC) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemPacientes::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
    }
}
