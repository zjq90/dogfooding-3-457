package com.oa.claim.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oa.claim.entity.ClaimVoucherItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClaimVoucherItemMapper extends BaseMapper<ClaimVoucherItem> {

    List<ClaimVoucherItem> selectByClaimVoucherId(@Param("claimVoucherId") Integer claimVoucherId);

    void deleteByClaimVoucherId(@Param("claimVoucherId") Integer claimVoucherId);
}
