package com.server.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DateTime {

    @Column
    @NotEmpty
    private String date;

    @Column
    @NotEmpty
    private String time;

    @Builder
    public DateTime(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public LocalDate toLocalDate(String dateValue) {
        return LocalDate.parse(dateValue);
    }

    public LocalTime toLocalTime(String timeValue) {
        return LocalTime.parse(timeValue);
    }

    public LocalDateTime toTimeOnSale(DateTime startDateTime) {
        return LocalDateTime.of(toLocalDate(startDateTime.date), toLocalTime(startDateTime.time)).minusDays(7);
    }

    public LocalDateTime toTimeOffSale(DateTime startDateTime) {
        return LocalDateTime.of(toLocalDate(startDateTime.date), toLocalTime(startDateTime.time)).minusDays(1);
    }

}
