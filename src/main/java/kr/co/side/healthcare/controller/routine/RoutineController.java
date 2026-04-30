package kr.co.side.healthcare.controller.routine;

import jakarta.servlet.http.HttpSession;
import kr.co.side.healthcare.domain.CustomUserDetails;
import kr.co.side.healthcare.domain.routine.request.SaveRoutineReqVO;
import kr.co.side.healthcare.domain.routine.response.RoutineResVO;
import kr.co.side.healthcare.domain.routine.response.TemplateResVO;
import kr.co.side.healthcare.service.routine.RoutineServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routine")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineServiceImpl routineService;

    @GetMapping("/view/list")
    public ResponseEntity getRoutineList(Authentication authentication) {

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        String loginId = user.getUser().getLoginId();

        List<TemplateResVO> routineList = routineService.getRoutineList(loginId);

        return ResponseEntity.ok().body(routineList);
    }

    @GetMapping("/view/detail")
    public ResponseEntity getRoutineDetail(@RequestParam Long templateId, Authentication authentication) {

        List<TemplateResVO> routineDetailList = routineService.getRoutineDetail(templateId);
        return ResponseEntity.ok().body(routineDetailList);
    }

    @PostMapping("/save")
    public ResponseEntity saveRoutine(@RequestBody SaveRoutineReqVO req, Authentication authentication) {
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        String userId = user.getUser().getLoginId();

        routineService.saveRoutineTemplate(req, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/detail")
    public ResponseEntity updateRoutineDetail(@RequestBody SaveRoutineReqVO reqVO) {
        routineService.updateRoutineDetail(reqVO);
        return ResponseEntity.ok().build();
    }
}
