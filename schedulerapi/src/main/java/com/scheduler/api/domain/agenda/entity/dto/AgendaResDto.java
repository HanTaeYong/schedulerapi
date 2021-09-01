package com.scheduler.api.domain.agenda.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scheduler.api.common.model.jenum.AgendaStatus;
import com.scheduler.api.domain.agenda.entity.Agenda;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@ApiModel(description = "일정 조회 결과")
@Data
public class AgendaResDto {
    @ApiModelProperty(position = 1, required = true, value = "일정 아이디", example = "1")
    @NotNull
    private Long id;

    @ApiModelProperty(position = 2, required = true, value = "제목", example = "비즈니스나우 미팅")
    @NotNull
    private String title;

    @ApiModelProperty(position = 3, required = true, value = "시작일자", example = "2021-08-27")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate startDate;

    @ApiModelProperty(position = 4, value = "시작시간", example = "14:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;

    @ApiModelProperty(position = 5, value = "종료일자", example = "2021-08-28")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @ApiModelProperty(position = 6, value = "종료시간", example = "15:30")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;

    @ApiModelProperty(position = 7, value = "참석자", example = "윤재원, 한태용")
    private String participants;

    @ApiModelProperty(position = 8, value = "위치", example = "회의실")
    private String location;

    @ApiModelProperty(position = 9, value = "설명", example = "가나다라 abcd 1234")
    private String description;

    @ApiModelProperty(position = 10, required = true, value = "상태", example = "TODO")
    @NotNull
    private AgendaStatus status;

    public AgendaResDto(Agenda agenda) {
        this.id = agenda.getId();
        this.title = agenda.getTitle();
        this.startDate = agenda.getStartDate();
        this.startTime = agenda.getStartTime();
        this.endDate = agenda.getEndDate();
        this.endTime = agenda.getEndTime();
        this.participants = agenda.getParticipants();
        this.location = agenda.getLocation();
        this.description = agenda.getDescription();
        this.status = agenda.getStatus();
    }

}
