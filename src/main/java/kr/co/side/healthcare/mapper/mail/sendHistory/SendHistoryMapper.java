package kr.co.side.healthcare.mapper.mail.sendHistory;

import kr.co.side.healthcare.domain.mail.history.SendMailHistoryVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SendHistoryMapper {
    void insertSendHistory(SendMailHistoryVO sendMailHistoryVO);

    void updateMailSuccessYn(SendMailHistoryVO sendMailHistoryVO);
}
