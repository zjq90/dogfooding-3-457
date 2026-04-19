package com.oa.sysapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oa.sysapi.entity.DealRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DealRecordMapper extends BaseMapper<DealRecord> {

    List<DealRecord> selectByClaimVoucherId(@Param("claimVoucherId") Integer claimVoucherId);
}
