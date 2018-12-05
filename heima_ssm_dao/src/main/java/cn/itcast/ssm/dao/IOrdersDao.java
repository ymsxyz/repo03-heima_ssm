package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Member;
import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IOrdersDao {

    //查询所有订单
    @Select("select * from orders")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "cn.itcast.ssm.dao.IProductDao.findById"))
    })
    List<Orders> findAll();

    //查询订单详细信息通过id
    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "cn.itcast.ssm.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId", javaType = Member.class, one = @One(select = "cn.itcast.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "cn.itcast.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(String id);
}
