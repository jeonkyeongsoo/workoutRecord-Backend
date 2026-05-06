package kr.co.side.healthcare.controller.calendar;

import kr.co.side.healthcare.domain.CustomUserDetails;
import kr.co.side.healthcare.domain.calendar.ReqCalendarVO;
import kr.co.side.healthcare.domain.calendar.ResCalendarVO;
import kr.co.side.healthcare.service.calendar.CalendarServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarServiceImpl calendarService;

    @GetMapping("/workout/list")
    public ResponseEntity getWorkoutList(Authentication authentication) {

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        String loginId = user.getUser().getLoginId();

        List<ResCalendarVO> resList = calendarService.getList(loginId);

        return ResponseEntity.ok().body(resList);
    }

    @PostMapping("/workout/save")
    public ResponseEntity saveWorkout(Authentication authentication, @RequestBody ReqCalendarVO reqVO) {

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        String loginId = user.getUser().getLoginId();

        calendarService.saveWorkout(reqVO, loginId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/workout/update")
    public ResponseEntity updateWorkoutUpdate(@RequestBody ReqCalendarVO reqVO) {
        calendarService.updateWorkoutDetail(reqVO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/workout/delete/{scheduleId}")
    public ResponseEntity deleteWorkout(@PathVariable Long scheduleId) {
        calendarService.deleteWorkoutDetail(scheduleId);
        return ResponseEntity.ok().build();
    }

}
