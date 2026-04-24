package kr.co.side.healthcare.domain.routine.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetResVO {

    private int setNo;
    private double plannedWeightKg;
    private int plannedReps;
}
