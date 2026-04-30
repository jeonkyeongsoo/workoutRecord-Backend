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

import java.util.*;

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
            throw new CustomException("루틴저장중 오류가 발생했습니다.", 400);
        }
    }

    @Override
    public List<TemplateResVO> getRoutineList(String loginId) {
        List<RoutineResVO> routineResVOList = routineRepo.getRoutineList(loginId);

        Map<Long, TemplateResVO> templateResMap = new HashMap<>();
        Map<Long, Map<Long, ExerciseResVO>> exerciseResByTemplateMap = new HashMap<>();

        for(RoutineResVO routineResVO : routineResVOList){
            String label = ExerciseCategoryEnum.getLabel(routineResVO.getExCategoryCd());

            TemplateResVO template = templateResMap.get(routineResVO.getTemplateId());

            if(template == null){
                template = new TemplateResVO();
                template.setTemplateId(routineResVO.getTemplateId());
                template.setTemplateName(routineResVO.getTemplateName());
                template.setExCategoryName(label);
                template.setExercises(new ArrayList<>());

                templateResMap.put(routineResVO.getTemplateId(), template);
                exerciseResByTemplateMap.put(routineResVO.getTemplateId(), new HashMap<>());
            }

            Map<Long, ExerciseResVO> exerciseResMap = exerciseResByTemplateMap.get(routineResVO.getTemplateId());
            ExerciseResVO exercise = exerciseResMap.get(routineResVO.getTemplateItemId());

            if(exercise == null) {
                exercise = new ExerciseResVO();
                exercise.setExerciseItemName(routineResVO.getExerciseItemName());
                exercise.setTemplateItemId(routineResVO.getTemplateItemId());
                exercise.setSortOrder(routineResVO.getSortOrder());
                exercise.setSets(new ArrayList<>());

                exerciseResMap.put(routineResVO.getTemplateItemId(), exercise);
                template.getExercises().add(exercise);
            }

            SetResVO set = new SetResVO();
            set.setSetNo(routineResVO.getSetNo());
            set.setPlannedWeightKg(routineResVO.getPlannedWeightKg());
            set.setPlannedReps(routineResVO.getPlannedReps());

            exercise.getSets().add(set);
        }

        return new ArrayList<>(templateResMap.values());
    }

    @Override
    public List<TemplateResVO> getRoutineDetail(Long templateId) {
        List<RoutineResVO> detailList = routineRepo.getRoutineDetail(templateId);

        Map<Long, TemplateResVO> templateResMap = new HashMap<>();
        Map<Long, Map<Long, ExerciseResVO>> exerciseResByTemplateMap = new HashMap<>();

        for(RoutineResVO routine : detailList){
            String label = ExerciseCategoryEnum.getLabel(routine.getExCategoryCd());

            TemplateResVO template = templateResMap.get(routine.getTemplateId());

            if(template == null){
                template = new TemplateResVO();
                template.setTemplateId(routine.getTemplateId());
                template.setTemplateName(routine.getTemplateName());
                template.setExCategoryName(label);
                template.setExercises(new ArrayList<>());

                templateResMap.put(routine.getTemplateId(), template);
                exerciseResByTemplateMap.put(routine.getTemplateId(), new HashMap<>());
            }

            Map<Long, ExerciseResVO> exerciseMap = exerciseResByTemplateMap.get(routine.getTemplateId());
            ExerciseResVO exercise = exerciseMap.get(routine.getTemplateItemId());

            if(exercise == null){
                exercise = new ExerciseResVO();
                exercise.setExerciseItemName(routine.getExerciseItemName());
                exercise.setTemplateItemId(routine.getTemplateItemId());
                exercise.setSortOrder(routine.getSortOrder());
                exercise.setSets(new ArrayList<>());

                exerciseMap.put(routine.getTemplateItemId(), exercise);
                template.getExercises().add(exercise);
            }

            SetResVO set = new SetResVO();
            set.setTemplateSetId(routine.getTemplateSetId());
            set.setSetNo(routine.getSetNo());
            set.setPlannedWeightKg(routine.getPlannedWeightKg());
            set.setPlannedReps(routine.getPlannedReps());

            exercise.getSets().add(set);

        }

        return new ArrayList<>(templateResMap.values());
    }

    @Override
    @Transactional
    public void updateRoutineDetail(SaveRoutineReqVO reqVO) {
        // DB 에서 가지고 온 기존 운동에 대한 정보들
        List<RoutineResVO> routineDetailList = routineRepo.getRoutineDetail(reqVO.getTemplateId());

        // DB에서 가져온 setID
        Set<Long> existSetIdSet = new HashSet<>();
        Set<Long> existExerciseIdSet = new HashSet<>();

        // 요청에 대한 운동, 세트의 id 값
        Set<Long> reqExerciseIdSet = new HashSet<>();
        Set<Long> reqSetIdSet = new HashSet<>();

        // 요청 운동아이디에 대한 세트 아이디 set
        Map<Long, Set<Long>> reqSetIdByExerciseIdMap = new HashMap<>();

        // DB 에 있는 운동아이디에 대한 세트아이디 set
        Map<Long, Set<Long>> existExerciseMap = new HashMap<>();

        // 요청운동 for 문
        for(ExerciseReqVO exercise: reqVO.getExercises()){
            reqExerciseIdSet.add(exercise.getTemplateItemId());

            for(SetReqVO set: exercise.getSets()){
                reqSetIdByExerciseIdMap
                        .computeIfAbsent(exercise.getTemplateItemId(), k -> new HashSet<>())
                        .add(set.getTemplateSetId());

                reqSetIdSet.add(set.getTemplateSetId());
            }
        }
        // DB에서 가지고 온 리스트 for문
        for (RoutineResVO routine : routineDetailList) {
            existSetIdSet.add(routine.getTemplateSetId());
            existExerciseIdSet.add(routine.getTemplateItemId());

            existExerciseMap
                    .computeIfAbsent(routine.getTemplateItemId(), key-> new HashSet<>())
                    .add(routine.getTemplateSetId());
        }

        List<Long> delExerciseList = existExerciseIdSet.stream().filter(id -> !reqExerciseIdSet.contains(id)).toList(); // 삭제될 운동아이디.
        List<Long> updateExerciseList = existExerciseIdSet.stream().filter(reqExerciseIdSet::contains).toList(); // 업데이트 할 운동아이디
        List<Long> insertExerciseList = reqExerciseIdSet.stream().filter(Objects::isNull).toList(); // 신규 운동아이디.

        try{
            for (ExerciseReqVO exercise : reqVO.getExercises()) {

                // 업데이트먼저
                if(updateExerciseList.contains(exercise.getTemplateItemId())){
                    RoutineTemplateItemVO updateRtiVO = new RoutineTemplateItemVO();
                    updateRtiVO.setTemplateItemId(exercise.getTemplateItemId());
                    updateRtiVO.setExerciseItemName(exercise.getExerciseItemName());
                    updateRtiVO.setSortOrder(exercise.getSortOrder());
                    routineRepo.updateRoutineTemplateItem(updateRtiVO);

                    Set<Long> existSetId = existExerciseMap.getOrDefault(
                            exercise.getTemplateItemId(),
                            Collections.emptySet()
                    );

                    Set<Long> reqSetId = reqSetIdByExerciseIdMap.getOrDefault(
                            exercise.getTemplateItemId(),
                            Collections.emptySet()
                    );

                    // 1. 기존 세트 update
                    for (SetReqVO set : exercise.getSets()) {
                        if (set.getTemplateSetId() != null) {
                            RoutineTemplateSetVO updateSetVO = new RoutineTemplateSetVO();
                            updateSetVO.setTemplateSetId(set.getTemplateSetId());
                            updateSetVO.setSetNo(set.getSetNo());
                            updateSetVO.setPlannedWeightKg(set.getPlannedWeightKg());
                            updateSetVO.setPlannedReps(set.getPlannedReps());

                            routineRepo.updateRoutineTemplateSet(updateSetVO);
                        }
                    }

                    // 2. 신규 세트 insert
                    for (SetReqVO set : exercise.getSets()) {
                        if (set.getTemplateSetId() == null) {
                            RoutineTemplateSetVO insertSetVO = new RoutineTemplateSetVO();
                            insertSetVO.setTemplateItemId(exercise.getTemplateItemId());
                            insertSetVO.setSetNo(set.getSetNo());
                            insertSetVO.setPlannedWeightKg(set.getPlannedWeightKg());
                            insertSetVO.setPlannedReps(set.getPlannedReps());

                            routineRepo.saveRoutineTemplateSet(insertSetVO);
                        }
                    }

                    // 3. 삭제 세트 delete
                    Set<Long> deleteSetIds = new HashSet<>(existSetId);
                    deleteSetIds.removeAll(reqSetId);

                    for (Long deleteSetId : deleteSetIds) {
                        if (deleteSetId != null) {
                            routineRepo.deleteSet(deleteSetId);
                        }
                    }

                }

                // insert(운동)
                if(insertExerciseList.contains(exercise.getTemplateItemId())){
                    RoutineTemplateItemVO insertRtiVO = new RoutineTemplateItemVO();
                    insertRtiVO.setTemplateId(reqVO.getTemplateId());
                    insertRtiVO.setExerciseItemName(exercise.getExerciseItemName());
                    insertRtiVO.setSortOrder(exercise.getSortOrder());
                    routineRepo.saveRoutineTemplateItem(insertRtiVO);

                    RoutineTemplateSetVO insertRtsVO = new RoutineTemplateSetVO();
                    insertRtsVO.setTemplateItemId(insertRtiVO.getTemplateItemId());
                    exercise.getSets().forEach(set -> {
                        reqSetIdSet.stream().filter(Objects::isNull).forEach(id -> {
                            insertRtsVO.setSetNo(set.getSetNo());
                            insertRtsVO.setPlannedWeightKg(set.getPlannedWeightKg());
                            insertRtsVO.setPlannedReps(set.getPlannedReps());
                            routineRepo.saveRoutineTemplateSet(insertRtsVO);
                        });
                    });
                }
            }

            for (Long deleteExerciseId : delExerciseList) {
                routineRepo.deleteSetByTemplateItemId(deleteExerciseId);
                routineRepo.deleteExercise(deleteExerciseId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("루틴 변경 중 오류가 발생했습니다.", 400);
        }

    }
}
