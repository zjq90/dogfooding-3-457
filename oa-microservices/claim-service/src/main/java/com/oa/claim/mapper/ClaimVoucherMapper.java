package com.oa.claim.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oa.claim.entity.ClaimVoucher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClaimVoucherMapper extends BaseMapper<ClaimVoucher> {

    List<ClaimVoucher> selectByCreateId(@Param("createId") String createId);

    List<ClaimVoucher> selectByNextDealId(@Param("nextDealId") String nextDealId);
}
