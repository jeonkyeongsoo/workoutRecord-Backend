package kr.co.side.healthcare.controller.routine;

import jakarta.servlet.http.HttpSession;
import kr.co.side.healthcare.domain.CustomUserDetails;
import kr.co.side.healthcare.domain.routine.request.SaveRoutineReqVO;
import kr.co.side.healthcare.domain.routine.response.RoutineResVO;
import kr.co.side.healthcare.service.routine.RoutineServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routine")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineServiceImpl routineService;

    @GetMapping("/view/list")
    public ResponseEntity getRoutineList(Authentication authentication) {

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        String loginId = user.getUser().getLoginId();

        routineService.getRoutineList(loginId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public ResponseEntity saveRoutine(@RequestBody SaveRoutineReqVO req, Authentication authentication) {
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        String userId = user.getUser().getLoginId();

        routineService.saveRoutineTemplate(req, userId);
        return ResponseEntity.ok().build();
    }
}
