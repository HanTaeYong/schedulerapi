package com.scheduler.api.domain.agenda;

import com.scheduler.api.common.model.response.CommonResult;
import com.scheduler.api.common.service.ResponseService;
import com.scheduler.api.domain.agenda.entity.dto.AgendaDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"02. 일정"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final ResponseService responseService;
    private final AgendaService agendaService;

    @ApiOperation(value = "일정 등록")
    @PostMapping(value = "/{id}")
    public CommonResult addAgenda(@ApiParam(required = true, value = "사용자 아이디") @PathVariable Long id,
                                  @ApiParam(required = true, value = "일정 등록 정보") @Valid @RequestBody AgendaDto reqDto){
        return responseService.getSingleResult(agendaService.addAgenda(id, reqDto));
    }

    @ApiOperation(value = "일정 조회(캘린더 화면)")
    @GetMapping(value = "/{id}/calendar")
    public CommonResult findAgenda(@ApiParam(required = true, value = "사용자 아이디") @PathVariable Long id,
                                   @ApiParam(required = true, value = "조회 시작일") @RequestParam(required = true) String startRangeDate,
                                   @ApiParam(required = true, value = "조회 종료일") @RequestParam(required = true) String endRangeDate){
        return responseService.getSingleResult(agendaService.findAgenda(id, startRangeDate, endRangeDate));
    }

    @ApiOperation(value = "일정 조회(일정 화면)")
    @GetMapping(value = "/{id}/list")
    public CommonResult findAgenda(@ApiParam(required = true, value = "사용자 아이디") @PathVariable Long id,
                                   @ApiParam(value = "일정 제목") @RequestParam(required = false) String title,
                                   @PageableDefault(size = 10, sort = "startDate", direction = Sort.Direction.ASC) Pageable pageable) {
        return responseService.getSingleResult(agendaService.findAgenda(id, title, pageable));
    }

    @ApiOperation(value = "일정 수정")
    @PutMapping(value = "/{id}")
    public CommonResult updateAgenda(@ApiParam(required = true, value = "일정 아이디") @PathVariable Long id,
                                     @ApiParam(required = true, value = "일정 수정 정보") @Valid @RequestBody AgendaDto reqDto){
        return responseService.getSingleResult(agendaService.updateAgenda(id, reqDto));
    }

    @ApiOperation(value = "일정 삭제")
    @DeleteMapping(value = "/{id}")
    public CommonResult deleteAgenda(@ApiParam(required = true, value = "일정 아이디") @PathVariable Long id){
        return responseService.getSingleResult(agendaService.deleteAgenda(id));
    }
}
