package med.voll.api.repository;

import med.voll.api.entity.Agenda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    Page<Agenda> findAllByStatusTrue(Pageable paginacao);
}
