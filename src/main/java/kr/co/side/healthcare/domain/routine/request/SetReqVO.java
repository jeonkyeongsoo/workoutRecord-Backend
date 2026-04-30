package kr.co.side.healthcare.domain.routine.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SetReqVO {

    private Long templateSetId;
    private int setNo;
    private double plannedWeightKg;
    private int plannedReps;
}
