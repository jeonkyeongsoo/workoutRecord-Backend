package kr.co.side.healthcare.domain.calendar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReqCalendarVO {
    private String title;
    private String loginId;
    private Long templateId;
    private String workoutDate;
    private String memo;
    private String status;
}
