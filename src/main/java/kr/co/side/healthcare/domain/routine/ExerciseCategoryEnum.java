package kr.co.side.healthcare.domain.routine;

import kr.co.side.healthcare.common.exception.CustomException;

public enum ExerciseCategoryEnum {
    EC01("가슴"),
    EC02("등"),
    EC03("어깨"),
    EC04("하체"),
    EC05("이두"),
    EC06("삼두"),
    EC07("복근");

    private final String label;

    ExerciseCategoryEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }


    public static ExerciseCategoryEnum getEnum(String label) {
        for (ExerciseCategoryEnum ec : ExerciseCategoryEnum.values()) {
            if (ec.getLabel().equals(label)) {
                return ec;
            }
        }
        throw new IllegalArgumentException("운동항목이 없습니다. 다시 시도해주세요.");
    }

    public static String getLabel(String code) {
        for (ExerciseCategoryEnum e : ExerciseCategoryEnum.values()) {
            if(e.name().equals(code)) {
                return e.getLabel();
            }
        }
        throw new IllegalArgumentException("운동항목 이름 변경중 오류가 발생했습니다.");
    }
}
