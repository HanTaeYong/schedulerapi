package com.scheduler.api.domain.agenda.entity;

import com.scheduler.api.common.model.BaseDateEntity;
import com.scheduler.api.common.model.jenum.AgendaStatus;
import com.scheduler.api.common.model.jenum.ColDel;
import com.scheduler.api.domain.agenda.entity.dto.AgendaDto;
import com.scheduler.api.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_agenda")
public class Agenda extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agenda_id", columnDefinition = "BIGINT")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", columnDefinition = "BIGINT")
    private Member member;

    @Column(name = "title", nullable = false, columnDefinition = "NVARCHAR(64)")
    private String title;

    @Column(name = "start_date", nullable = false, columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(name = "start_Time", columnDefinition = "TIME")
    private LocalTime startTime;

    @Column(name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;

    @Column(name = "end_time", columnDefinition = "TIME")
    private LocalTime endTime;

    @Column(name = "participants", columnDefinition = "NVARCHAR(128)")
    private String participants;

    @Column(name = "location", columnDefinition = "NVARCHAR(64)")
    private String location;

    @Column(name = "description", columnDefinition = "NVARCHAR(1024)")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", columnDefinition = "NVARCHAR(10)")
    private AgendaStatus status;

    @Column(name = "deleted_dttm", columnDefinition = "DATETIME")
    private LocalDateTime deletedDttm;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "isdel", nullable = false, columnDefinition = "NVARCHAR(10) default 'USE'")
    private ColDel isDel;

    public void setMember(Member member) {
        this.member = member;
    }

    public void update(AgendaDto reqDto) {
        this.title = reqDto.getTitle();;
        this.startDate = reqDto.getStartDate();
        this.startTime = reqDto.getStartTime();
        this.endDate = reqDto.getEndDate();
        this.endTime = reqDto.getEndTime();
        this.participants = reqDto.getParticipants();
        this.location = reqDto.getLocation();
        this.description = reqDto.getDescription();
        this.status = reqDto.getStatus();
    }

    public void setIsDel() {
        this.isDel = ColDel.DEL;
        this.deletedDttm = LocalDateTime.now();
    }
}
