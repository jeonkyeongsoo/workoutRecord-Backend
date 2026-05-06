package kr.co.side.healthcare.service.calendar;

import kr.co.side.healthcare.domain.calendar.ReqCalendarVO;
import kr.co.side.healthcare.domain.calendar.ResCalendarVO;

import java.util.List;

public interface CalendarService {
    List<ResCalendarVO> getList(String loginId);

    void saveWorkout(ReqCalendarVO reqVO, String loginId);

    void deleteWorkoutDetail(Long scheduleId);

    void updateWorkoutDetail(ReqCalendarVO reqVO);
}
