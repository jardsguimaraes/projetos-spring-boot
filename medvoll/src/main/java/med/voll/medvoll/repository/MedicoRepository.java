package med.voll.medvoll.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.medvoll.model.MedicoModel;

public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {

    Page<MedicoModel> findAllByAtivoTrue(Pageable paginacao);

}
