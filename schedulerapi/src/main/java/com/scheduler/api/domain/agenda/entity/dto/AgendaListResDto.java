package com.scheduler.api.domain.agenda.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scheduler.api.common.model.jenum.AgendaStatus;
import com.scheduler.api.common.model.jenum.ColDel;
import com.scheduler.api.domain.agenda.entity.Agenda;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "일정 리스트 조회 결과")
@Data
public class AgendaListResDto {
    @ApiModelProperty(required = true, value = "일정 리스트")
    @NotNull
    private List<AgendaResDto> agendas = new ArrayList<>();

    @ApiModelProperty(value = "전체 갯수")
    Long totalElements;

    @ApiModelProperty(value = "마지막 여부", example = "false")
    boolean last;

    @ApiModelProperty(value = "전체 페이지 수")
    Integer totalPages;

    @ApiModelProperty(value = "현재 페이지 조회수")
    Integer numberOfElements;

    public AgendaListResDto(List<Agenda> agendas) {
        for (Agenda agenda : agendas) {
            this.agendas.add(new AgendaResDto(agenda));
        }
    }

    public AgendaListResDto(Page<Agenda> agendaPage) {
        totalPages = agendaPage.getTotalPages();
        numberOfElements = agendaPage.getNumberOfElements();
        totalElements = agendaPage.getTotalElements();
        last = agendaPage.isLast();

        for (Agenda agenda : agendaPage) {
            this.agendas.add(new AgendaResDto(agenda));
        }
    }
}
