package kr.co.side.healthcare.domain.routine;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoutineTemplateVO {

    private Long templateId;
    private String userId;
    private String templateName;
    private ExerciseCategoryEnum exCategoryCd;

}
