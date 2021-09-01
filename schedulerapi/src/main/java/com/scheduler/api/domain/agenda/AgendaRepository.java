package com.scheduler.api.domain.agenda;

import com.scheduler.api.common.model.jenum.ColDel;
import com.scheduler.api.domain.agenda.entity.Agenda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    List<Agenda> findByMember_IdAndStartDateBetweenAndIsDelOrMember_IdAndEndDateBetweenAndIsDelOrderByStartDateAsc
            (Long memberId1, LocalDate startRangeDate1, LocalDate endRangeDate1, ColDel isDel1,
             Long memberId2, LocalDate startRangeDate2, LocalDate endRangeDate2, ColDel isDel2);
    Page<Agenda> findByMember_IdAndIsDel(Long id, ColDel isDel, Pageable pageable);
    Page<Agenda> findByMember_IdAndIsDelAndTitleContaining(Long id, ColDel isDel, String title, Pageable pageable);

    Optional<Agenda> findByIdAndIsDel(Long id, ColDel isDel);
}
