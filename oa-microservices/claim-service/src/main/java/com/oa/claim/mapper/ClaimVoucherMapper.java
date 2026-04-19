package com.oa.claim.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oa.claim.entity.ClaimVoucher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClaimVoucherMapper extends BaseMapper<ClaimVoucher> {

    @Select("SELECT cv.*, e.name as creator_name, e2.name as dealer_name " +
            "FROM claim_voucher cv " +
            "LEFT JOIN employee e ON cv.create_id = e.id " +
            "LEFT JOIN employee e2 ON cv.next_deal_id = e2.id " +
            "WHERE cv.create_id = #{createId} ORDER BY cv.create_time DESC")
    List<ClaimVoucher> selectByCreateId(@Param("createId") String createId);

    @Select("SELECT cv.*, e.name as creator_name, e2.name as dealer_name " +
            "FROM claim_voucher cv " +
            "LEFT JOIN employee e ON cv.create_id = e.id " +
            "LEFT JOIN employee e2 ON cv.next_deal_id = e2.id " +
            "WHERE cv.next_deal_id = #{nextDealId} ORDER BY cv.create_time DESC")
    List<ClaimVoucher> selectByNextDealId(@Param("nextDealId") String nextDealId);

    @Select("SELECT cv.*, e.name as creator_name, e2.name as dealer_name " +
            "FROM claim_voucher cv " +
            "LEFT JOIN employee e ON cv.create_id = e.id " +
            "LEFT JOIN employee e2 ON cv.next_deal_id = e2.id " +
            "WHERE cv.id = #{id}")
    ClaimVoucher selectByIdWithNames(@Param("id") Integer id);
}
