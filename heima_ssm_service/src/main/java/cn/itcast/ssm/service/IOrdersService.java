package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {

    //查询所有订单,分页
    List<Orders> findAll(int page, int size) throws Exception;

    //根据id查询订单详细信息
    Orders findById(String id) throws Exception;
}
