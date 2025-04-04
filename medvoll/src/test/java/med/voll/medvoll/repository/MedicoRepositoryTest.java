package med.voll.medvoll.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import med.voll.medvoll.dtos.endereco.DadosEndereco;
import med.voll.medvoll.dtos.medico.DadosCadastroMedico;
import med.voll.medvoll.dtos.paciente.DadosCadastroPaciente;
import med.voll.medvoll.enumeracoes.EspecialidadeMedica;
import med.voll.medvoll.model.Consulta;
import med.voll.medvoll.model.Medico;
import med.voll.medvoll.model.Paciente;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria retornar null quando unico médico cadastrado não esta disponivel na data informada")
    void escolherMedicoAleatorioLivreDataCenario1() {
        //given ou arrange
        var medico = cadastrarMedico("medico", "medico@voll.med", "123456", EspecialidadeMedica.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@voll.med", "664646464646");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10());

        //when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreData(EspecialidadeMedica.GINECOLOGIA, proximaSegundaAs10());

        //then ou assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria retornar médico quando ele estiver disponivel na data")
    void escolherMedicoAleatorioLivreDataCenario2() {
        //given ou arrange
        var medico = cadastrarMedico("medico", "medico@voll.med", "123456", EspecialidadeMedica.CARDIOLOGIA);

        //when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreData(EspecialidadeMedica.CARDIOLOGIA, proximaSegundaAs10());

        //then ou assert
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private LocalDateTime proximaSegundaAs10() {
        return LocalDate.now()
            .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
            .atTime(10, 0);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data, true, null));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, EspecialidadeMedica especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, EspecialidadeMedica especialidade) {
        return new DadosCadastroMedico(
            nome,
            email,
            "656446464",
            crm,
            especialidade,
            dadosEndereco());
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
            nome,
            email,
            "6446464646",
            cpf,
            dadosEndereco());
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "000000-00",
                "Maceió",
                "AL",
                null,
                null);
    }
}
