package com.oa.starter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oa.common.entity.ClaimVoucher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClaimVoucherMapper extends BaseMapper<ClaimVoucher> {

    ClaimVoucher selectWithDetails(@Param("id") Integer id);

    List<ClaimVoucher> selectByCreateId(@Param("createId") String createId);

    List<ClaimVoucher> selectByNextDealId(@Param("nextDealId") String nextDealId);
}
