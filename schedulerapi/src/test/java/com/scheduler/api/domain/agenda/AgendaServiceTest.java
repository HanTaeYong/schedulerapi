package com.scheduler.api.domain.agenda;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AgendaServiceTest {

    @Test
    public void test() throws Exception{
        String sDate_str = "2021-08-26";
        LocalDate sDate = LocalDate.parse(sDate_str);
        System.out.println("sDate = " + sDate);
        System.out.println(sDate.plusWeeks(1));
        System.out.println(sDate.withDayOfMonth(1));
        System.out.println(sDate.withDayOfMonth(sDate.lengthOfMonth()));

//        System.out.println(sDate.withMonth(1));
//        System.out.println(sDate.withYear(1));
//        System.out.println(sDate.withDayOfYear(1));
    }
}