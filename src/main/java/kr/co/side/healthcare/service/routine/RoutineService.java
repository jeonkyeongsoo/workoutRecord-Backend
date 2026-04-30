package kr.co.side.healthcare.service.routine;

import kr.co.side.healthcare.domain.routine.RoutineTemplateVO;
import kr.co.side.healthcare.domain.routine.request.SaveRoutineReqVO;
import kr.co.side.healthcare.domain.routine.response.TemplateResVO;

import java.util.List;

public interface RoutineService {
    void saveRoutineTemplate(SaveRoutineReqVO req, String userId);

    List<TemplateResVO> getRoutineList(String loginId);

    List<TemplateResVO> getRoutineDetail(Long templateId);

    void updateRoutineDetail(SaveRoutineReqVO reqVO);
}
