package kr.co.side.healthcare.repository.calendar;

import kr.co.side.healthcare.domain.calendar.ReqCalendarVO;
import kr.co.side.healthcare.domain.calendar.ResCalendarVO;
import kr.co.side.healthcare.mapper.calendar.CalendarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CalendarRepoImpl implements CalendarRepo{

    private final CalendarMapper calendarMapper;

    @Override
    public List<ResCalendarVO> getList(String loginId) {
        return calendarMapper.getList(loginId);
    }

    @Override
    public void saveWorkout(ReqCalendarVO reqCalendarVO) {
        calendarMapper.saveWorkout(reqCalendarVO);
    }
}
