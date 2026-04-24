package kr.co.side.healthcare.service.routine;

import kr.co.side.healthcare.common.exception.CustomException;
import kr.co.side.healthcare.domain.routine.ExerciseCategoryEnum;
import kr.co.side.healthcare.domain.routine.RoutineTemplateItemVO;
import kr.co.side.healthcare.domain.routine.RoutineTemplateSetVO;
import kr.co.side.healthcare.domain.routine.RoutineTemplateVO;
import kr.co.side.healthcare.domain.routine.request.ExerciseReqVO;
import kr.co.side.healthcare.domain.routine.request.SaveRoutineReqVO;
import kr.co.side.healthcare.domain.routine.request.SetReqVO;
import kr.co.side.healthcare.domain.routine.response.ExerciseResVO;
import kr.co.side.healthcare.domain.routine.response.RoutineResVO;
import kr.co.side.healthcare.domain.routine.response.SetResVO;
import kr.co.side.healthcare.domain.routine.response.TemplateResVO;
import kr.co.side.healthcare.repository.routine.routineRepoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RoutineServiceImpl implements RoutineService {

    private final routineRepoImpl routineRepo;

    @Override
    @Transactional
    public void saveRoutineTemplate(SaveRoutineReqVO req, String userId) {

        try{
            RoutineTemplateVO routineTemplateVO = new RoutineTemplateVO();
            routineTemplateVO.setTemplateName(req.getTemplateName());
            routineTemplateVO.setUserId(userId);
            routineTemplateVO.setExCategoryCd(
                    ExerciseCategoryEnum.getEnum(req.getExCategoryName())
            );

            int templateN = routineRepo.saveRoutineTemplate(routineTemplateVO);
            Long templateId = routineTemplateVO.getTemplateId();

            for(ExerciseReqVO exerciseReqVO : req.getExercises()){
                RoutineTemplateItemVO routineTemplateItemVO = new RoutineTemplateItemVO();
                routineTemplateItemVO.setTemplateId(templateId);
                routineTemplateItemVO.setExerciseItemName(exerciseReqVO.getExerciseItemName());
                routineTemplateItemVO.setSortOrder(exerciseReqVO.getSortOrder());
                int ItemN = routineRepo.saveRoutineTemplateItem(routineTemplateItemVO);

                Long templateItemId = routineTemplateItemVO.getTemplateItemId();

                for(SetReqVO setReqVO : exerciseReqVO.getSets()){
                    RoutineTemplateSetVO routineTemplateSetVO = new RoutineTemplateSetVO();
                    routineTemplateSetVO.setTemplateItemId(templateItemId);
                    routineTemplateSetVO.setSetNo(setReqVO.getSetNo());
                    routineTemplateSetVO.setPlannedWeightKg(setReqVO.getPlannedWeightKg());
                    routineTemplateSetVO.setPlannedReps(setReqVO.getPlannedReps());

                    routineRepo.saveRoutineTemplateSet(routineTemplateSetVO);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new CustomException("데이터 저장중 오류가 발생했습니다. 다시 시도해주세요.", 400);
        }
    }

    @Override
    public void getRoutineList(String loginId) {
        List<RoutineResVO> routineResVOList = routineRepo.getRoutineList(loginId);

        for(RoutineResVO routineResVO : routineResVOList){
            String exCategoryName = ExerciseCategoryEnum.getLabel(routineResVO.getExCategoryCd());

            Map<Long, TemplateResVO> templateResMap = new HashMap<>();
            Map<Long, ExerciseResVO> exerciseResMap = new HashMap<>();


            SetResVO setResVO = new SetResVO();




            TemplateResVO templateResVO = new TemplateResVO();
            templateResVO.setTemplateName(routineResVO.getTemplateName());
            templateResVO.setExCategoryName(exCategoryName);


        }
    }
}
