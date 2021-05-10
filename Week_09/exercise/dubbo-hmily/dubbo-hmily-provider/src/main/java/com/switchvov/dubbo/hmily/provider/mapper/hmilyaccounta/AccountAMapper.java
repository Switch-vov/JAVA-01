package com.switchvov.dubbo.hmily.provider.mapper.hmilyaccounta;

import com.switchvov.dubbo.hmily.common.domain.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author switch
 * @since 2021/5/10
 */
@Repository
@Mapper
public interface AccountAMapper {
    List<Account> list();

    Account getByUserId(@Param("userId") String userId);

    int changeUsd(
            @Param("userId") String userId,
            @Param("usd") BigDecimal usd
    );

    int usdToCny(
            @Param("userId") String userId,
            @Param("usd") BigDecimal usd,
            @Param("cny") BigDecimal cny
    );

    int cnyToUsd(
            @Param("userId") String userId,
            @Param("usd") BigDecimal usd,
            @Param("cny") BigDecimal cny
    );
}
