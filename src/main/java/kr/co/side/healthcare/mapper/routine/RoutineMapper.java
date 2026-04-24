package kr.co.side.healthcare.mapper.routine;

import kr.co.side.healthcare.domain.routine.RoutineTemplateItemVO;
import kr.co.side.healthcare.domain.routine.RoutineTemplateSetVO;
import kr.co.side.healthcare.domain.routine.RoutineTemplateVO;
import kr.co.side.healthcare.domain.routine.response.RoutineResVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoutineMapper {
    int saveRoutineTemplate(RoutineTemplateVO routineTemplateVO);

    int saveRoutineTemplateItem(RoutineTemplateItemVO routineTemplateItemVO);

    void saveRoutineTemplateSet(RoutineTemplateSetVO routineTemplateSetVO);

    List<RoutineResVO> getRoutineList(String loginId);
}
