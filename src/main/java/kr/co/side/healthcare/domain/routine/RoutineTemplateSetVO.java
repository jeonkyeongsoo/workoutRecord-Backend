package kr.co.side.healthcare.domain.routine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoutineTemplateSetVO {

    private Long templateItemId;
    private Long templateSetId;
    private int setNo;
    private int plannedReps;
    private double plannedWeightKg;

}
