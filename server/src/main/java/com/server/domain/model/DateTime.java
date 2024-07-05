package com.server.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DateTime {

    @Column
    private String date;

    @Column String time;

    @Builder
    public DateTime(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public LocalDate toLocalDate(String dateValue) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        return LocalDate.parse(dateValue, formatter);
    }

    public LocalTime toLocalTime(String timeValue) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분");
        return LocalTime.parse(timeValue);
    }

}
