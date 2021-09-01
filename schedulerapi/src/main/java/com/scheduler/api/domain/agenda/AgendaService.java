package com.scheduler.api.domain.agenda;

import com.scheduler.api.common.exception.AgendaNotFoundIdException;
import com.scheduler.api.common.exception.AuthNotFoundIdException;
import com.scheduler.api.common.model.jenum.ColDel;
import com.scheduler.api.domain.agenda.entity.Agenda;
import com.scheduler.api.domain.agenda.entity.dto.AgendaDto;
import com.scheduler.api.domain.agenda.entity.dto.AgendaListResDto;
import com.scheduler.api.domain.member.entity.Member;
import com.scheduler.api.domain.member.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AgendaService {

    private final MemberRepository memberRepository;
    private final AgendaRepository agendaRepository;

    @Transactional
    public Long addAgenda(Long id, AgendaDto reqDto) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new AuthNotFoundIdException());

        if (reqDto.getEndDate().toString().isBlank()) {
            reqDto.setEndDate(reqDto.getStartDate());
        }
        Agenda agenda = reqDto.toEntity();
        agenda.setMember(member);

        return agendaRepository.save(agenda).getId();
    }

    @Transactional
    public AgendaListResDto findAgenda(Long memberId, String startRangeDateStr, String endRangeDateStr) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new AuthNotFoundIdException());

        LocalDate startRangeDate = LocalDate.parse(startRangeDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endRangeDate = LocalDate.parse(endRangeDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<Agenda> agendas = agendaRepository.findByMember_IdAndStartDateBetweenAndIsDelOrMember_IdAndEndDateBetweenAndIsDelOrderByStartDateAsc
                (memberId, startRangeDate, endRangeDate, ColDel.USE, memberId, startRangeDate, endRangeDate, ColDel.USE);
        return new AgendaListResDto(agendas);
    }

    @Transactional
    public AgendaListResDto findAgenda(Long memberId, String title, Pageable pageable) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new AuthNotFoundIdException());

        Page<Agenda> agendas2 = title == null ?
                agendaRepository.findByMember_IdAndIsDel(memberId, ColDel.USE, pageable) :
                agendaRepository.findByMember_IdAndIsDelAndTitleContaining(memberId, ColDel.USE, title, pageable);

        return new AgendaListResDto(agendas2);
    }

    @Transactional
    public Long updateAgenda(Long id, AgendaDto reqDto) {
        Agenda agenda = agendaRepository.findByIdAndIsDel(id, ColDel.USE).orElseThrow(() -> new AgendaNotFoundIdException());
        agenda.update(reqDto);

        return agenda.getId();
    }

    @Transactional
    public Long deleteAgenda(Long id) {
        Agenda agenda = agendaRepository.findById(id).orElseThrow(() -> new AgendaNotFoundIdException());
        agenda.setIsDel();

        return id;
    }
}
