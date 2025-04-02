package med.voll.medvoll.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.medvoll.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario,
            LocalDateTime ultimoHorario);

    boolean existsByMedicoIdAndDataAndMotivoIsNull(Long idMedico, LocalDateTime data);

    Page<Consulta> findByConfirmadaTrue(Pageable paginacao);
}
