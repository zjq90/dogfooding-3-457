package com.oa.claim.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oa.claim.entity.DealRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DealRecordMapper extends BaseMapper<DealRecord> {

    @Select("SELECT dr.*, e.name as dealer_name " +
            "FROM deal_record dr " +
            "LEFT JOIN employee e ON dr.deal_id = e.id " +
            "WHERE dr.claim_voucher_id = #{claimVoucherId} ORDER BY dr.deal_time")
    List<DealRecord> selectByClaimVoucherId(@Param("claimVoucherId") Integer claimVoucherId);
}
