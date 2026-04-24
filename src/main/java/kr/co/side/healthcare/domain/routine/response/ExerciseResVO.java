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
public class ExerciseResVO {

    private Long templateItemId;
    private String exerciseItemName;
    private int sortOrder;
    private List<SetResVO> sets;

}
