package com.switchvov.dubbo.hmily.provider.mapper.hmilyaccountfreeze;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author switch
 * @since 2021/5/10
 */
@Repository
@Mapper
public interface AccountFreezeMapper {
    int freeze(
            @Param("userId") String userId,
            @Param("cny") BigDecimal cny
    );

    int unfreeze(
            @Param("userId") String userId,
            @Param("cny") BigDecimal cny
    );
}
