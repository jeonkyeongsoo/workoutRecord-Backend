package kr.co.side.healthcare.mapper.calendar;

import kr.co.side.healthcare.domain.calendar.ReqCalendarVO;
import kr.co.side.healthcare.domain.calendar.ResCalendarVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalendarMapper {
    List<ResCalendarVO> getList(String loginId);

    void saveWorkout(ReqCalendarVO reqCalendarVO);

    void deleteWorkoutDetail(Long scheduleId);

    void updateWorkoutDetail(ReqCalendarVO reqVO);
}
