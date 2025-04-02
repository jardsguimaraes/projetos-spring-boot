package med.voll.medvoll.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.medvoll.enumeracoes.EspecialidadeMedica;
import med.voll.medvoll.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select
                m
            from
                Medico m
            where
                m.ativo = true and
                m.especialidade = :especialidade and
                m.id not in (select
                                c.medico.id
                            from
                                Consulta c
                            where
                                c.data = :data and
                                c.motivo is null)
            order by
                rand()
            limit 1
            """)
    Medico escolherMedicoAleatorioLivreData(EspecialidadeMedica especialidade);

    @Query("""
            select
                m.ativo
            from
                Medico m
            where
                m.id = :id
            """)
    boolean findAtivoById(Long id);
}
