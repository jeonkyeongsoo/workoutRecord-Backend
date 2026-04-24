package kr.co.side.healthcare.domain.routine.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoutineResVO {

    private String userId;
    private Long templateId;
    private String templateName;
    private String exCategoryCd;
    private Long templateItemId;
    private int sortOrder;
    private String exerciseItemName;
    private int setNo;
    private double plannedWeightKg;
    private int plannedReps;
}
