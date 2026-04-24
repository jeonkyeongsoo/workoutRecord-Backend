package kr.co.side.healthcare.repository.routine;

import kr.co.side.healthcare.domain.routine.RoutineTemplateItemVO;
import kr.co.side.healthcare.domain.routine.RoutineTemplateSetVO;
import kr.co.side.healthcare.domain.routine.RoutineTemplateVO;
import kr.co.side.healthcare.domain.routine.response.RoutineResVO;

import java.util.List;

public interface routineRepo {
    int saveRoutineTemplate(RoutineTemplateVO routineTemplateVO);

    int saveRoutineTemplateItem(RoutineTemplateItemVO routineTemplateItemVO);

    void saveRoutineTemplateSet(RoutineTemplateSetVO routineTemplateSetVO);

    List<RoutineResVO> getRoutineList(String loginId);
}
