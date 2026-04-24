package kr.co.side.healthcare.service.routine;

import kr.co.side.healthcare.domain.routine.RoutineTemplateVO;
import kr.co.side.healthcare.domain.routine.request.SaveRoutineReqVO;

public interface RoutineService {
    void saveRoutineTemplate(SaveRoutineReqVO req, String userId);

    void getRoutineList(String loginId);
}
