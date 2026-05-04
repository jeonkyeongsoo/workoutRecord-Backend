package kr.co.side.healthcare.repository.calendar;

import kr.co.side.healthcare.domain.calendar.ReqCalendarVO;
import kr.co.side.healthcare.domain.calendar.ResCalendarVO;

import java.util.List;

public interface CalendarRepo {
    List<ResCalendarVO> getList(String loginId);

    void saveWorkout(ReqCalendarVO reqCalendarVO);
}
