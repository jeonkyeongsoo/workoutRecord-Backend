package kr.co.side.healthcare.domain.calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResCalendarVO {

    private String scheduleName;
    private Long scheduleId;
    private Long templateId;
    private String scheduleDate;
    private String scheduleStatus;
    private String memo;
}
