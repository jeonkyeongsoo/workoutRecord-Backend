package kr.co.side.healthcare.domain.routine.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemplateResVO {

    private Long templateId;
    private String templateName;
    private String exCategoryName;
    private List<ExerciseResVO> exercises;
}
