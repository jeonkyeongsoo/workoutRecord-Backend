package kr.co.side.healthcare.domain.routine.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseReqVO {

    private String exerciseItemName;
    private int sortOrder;
    private List<SetReqVO> sets;
}
