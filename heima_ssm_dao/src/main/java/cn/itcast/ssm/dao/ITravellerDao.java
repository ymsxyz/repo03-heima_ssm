package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {

    //查询旅客信息通过orders表的id字段和中间表:多对多关系
    @Select("select * from traveller where id in(select travellerId from ORDER_TRAVELLER where orderId=#{orderId})")
    List<Traveller> findByOrdersId(String orderId) throws Exception;
}
