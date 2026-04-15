package kr.co.side.healthcare.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private String userId;
    private String loginId;
    private String password;
    private int roleId;
    private String createdAt;
    private String updatedAt;
    private String roleName;
    private String useYn;
    private String lockedYn;
    private int loginFailCount;
    private String deletedYn;
    private String email;
    private String phone;
    private String birth;
    private String name;
}
