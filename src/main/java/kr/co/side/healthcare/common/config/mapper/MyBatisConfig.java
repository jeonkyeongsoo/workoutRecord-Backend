package kr.co.side.healthcare.common.config.mapper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("kr.co.side.healthcare.**.mapper")
public class MyBatisConfig {
}
