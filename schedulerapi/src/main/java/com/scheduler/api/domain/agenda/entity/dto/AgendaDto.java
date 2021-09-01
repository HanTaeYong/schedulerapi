package com.scheduler.api.domain.agenda.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scheduler.api.common.model.jenum.AgendaStatus;
import com.scheduler.api.common.model.jenum.ColDel;
import com.scheduler.api.domain.agenda.entity.Agenda;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@ApiModel(description = "일정 등록/업데이트 요청")
@Data
public class AgendaDto {
    @ApiModelProperty(position = 1, required = true, value = "제목", example = "비즈니스나우 미팅")
    @NotNull
    private String title;

    @ApiModelProperty(position = 2, required = true, value = "시작일자", example = "2021-08-27")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate startDate;

    @ApiModelProperty(position = 3, value = "시작시간", example = "14:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;

    @ApiModelProperty(position = 4, value = "종료일자", example = "2021-08-28")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @ApiModelProperty(position = 5, value = "종료시간", example = "15:30")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;

    @ApiModelProperty(position = 6, value = "참석자", example = "윤재원, 한태용")
    private String participants;

    @ApiModelProperty(position = 7, value = "위치", example = "회의실")
    private String location;

    @ApiModelProperty(position = 8, value = "설명", example = "가나다라 abcd 1234")
    private String description;

    @ApiModelProperty(position = 9, required = true, value = "상태", example = "TODO")
    @NotNull
    private AgendaStatus status;

    public Agenda toEntity() {
        return Agenda.builder()
                .title(title)
                .startDate(startDate)
                .startTime(startTime)
                .endDate(endDate)
                .endTime(endTime)
                .participants(participants)
                .location(location)
                .description(description)
                .status(status)
                .isDel(ColDel.USE)
                .build();
    }
}
