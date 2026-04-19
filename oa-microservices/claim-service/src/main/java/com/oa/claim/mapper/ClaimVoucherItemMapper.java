package com.oa.claim.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oa.claim.entity.ClaimVoucherItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClaimVoucherItemMapper extends BaseMapper<ClaimVoucherItem> {

    @Select("SELECT * FROM claim_voucher_item WHERE claim_voucher_id = #{claimVoucherId}")
    List<ClaimVoucherItem> selectByClaimVoucherId(@Param("claimVoucherId") Integer claimVoucherId);

    @Select("DELETE FROM claim_voucher_item WHERE claim_voucher_id = #{claimVoucherId}")
    void deleteByClaimVoucherId(@Param("claimVoucherId") Integer claimVoucherId);
}
