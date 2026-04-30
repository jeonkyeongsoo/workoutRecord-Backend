package kr.co.side.healthcare.repository.routine;

import kr.co.side.healthcare.domain.routine.RoutineTemplateItemVO;
import kr.co.side.healthcare.domain.routine.RoutineTemplateSetVO;
import kr.co.side.healthcare.domain.routine.RoutineTemplateVO;
import kr.co.side.healthcare.domain.routine.response.RoutineResVO;
import kr.co.side.healthcare.mapper.routine.RoutineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class routineRepoImpl implements routineRepo {

    private final RoutineMapper routineMapper;

    @Override
    public int saveRoutineTemplate(RoutineTemplateVO routineTemplateVO) {
        return routineMapper.saveRoutineTemplate(routineTemplateVO);
    }

    @Override
    public int saveRoutineTemplateItem(RoutineTemplateItemVO routineTemplateItemVO) {
        return routineMapper.saveRoutineTemplateItem(routineTemplateItemVO);
    }

    @Override
    public void saveRoutineTemplateSet(RoutineTemplateSetVO routineTemplateSetVO) {
        routineMapper.saveRoutineTemplateSet(routineTemplateSetVO);
    }

    @Override
    public List<RoutineResVO> getRoutineList(String loginId) {
        return routineMapper.getRoutineList(loginId);
    }

    @Override
    public List<RoutineResVO> getRoutineDetail(Long templateId) {
        return routineMapper.getRoutineDetail(templateId);
    }

    @Override
    public void updateRoutineTemplateItem(RoutineTemplateItemVO routineTemplateItemVO) {
        routineMapper.updateRoutineTemplateItem(routineTemplateItemVO);
    }

    @Override
    public void updateRoutineTemplateSet(RoutineTemplateSetVO routineTemplateSetVO) {
        routineMapper.updateRoutineTemplateSet(routineTemplateSetVO);
    }

    @Override
    public void deleteSet(Long templateSetId) {
        routineMapper.deleteSet(templateSetId);
    }

    @Override
    public void deleteExercise(Long templateItemId) {
        routineMapper.deleteExercise(templateItemId);
    }

    @Override
    public void deleteSetByTemplateItemId(Long templateItemId) {
        routineMapper.deleteSetByTemplateItemId(templateItemId);
    }
}
