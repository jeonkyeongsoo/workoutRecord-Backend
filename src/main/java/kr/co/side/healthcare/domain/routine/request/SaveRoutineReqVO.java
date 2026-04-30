package kr.co.side.healthcare.domain.routine.request;

import kr.co.side.healthcare.domain.routine.ExerciseCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveRoutineReqVO {

    private Long templateId;
    private String templateName;
    private String exCategoryName;
    private List<ExerciseReqVO> exercises;

}
