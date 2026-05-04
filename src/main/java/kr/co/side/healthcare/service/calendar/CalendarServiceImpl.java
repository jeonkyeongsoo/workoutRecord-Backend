package kr.co.side.healthcare.service.calendar;

import kr.co.side.healthcare.common.exception.CustomException;
import kr.co.side.healthcare.domain.calendar.ReqCalendarVO;
import kr.co.side.healthcare.domain.calendar.ResCalendarVO;
import kr.co.side.healthcare.repository.calendar.CalendarRepoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService{

    private final CalendarRepoImpl calendarRepo;

    @Override
    public List<ResCalendarVO> getList(String loginId) {
        return calendarRepo.getList(loginId);
    }

    @Override
    public void saveWorkout(ReqCalendarVO reqVO, String loginId) {

        try {
            ReqCalendarVO reqCalendarVO = ReqCalendarVO.builder()
                    .title(reqVO.getTitle())
                    .templateId(reqVO.getTemplateId())
                    .loginId(loginId)
                    .workoutDate(reqVO.getWorkoutDate())
                    .memo(reqVO.getMemo())
                    .status(reqVO.getStatus())
                    .build();

            calendarRepo.saveWorkout(reqCalendarVO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("캘린더 저장 중 오류가 발생했습니다. 다시 시도해주시길 바랍니다.", 500);
        }

    }
}
