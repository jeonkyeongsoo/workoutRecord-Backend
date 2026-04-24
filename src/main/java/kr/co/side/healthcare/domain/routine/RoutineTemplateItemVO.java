package kr.co.side.healthcare.domain.routine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoutineTemplateItemVO {

    private Long templateId;
    private Long templateItemId;
    private String exerciseItemName;
    private int sortOrder;

}
