package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.IOrdersDao;
import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional//开启事务
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //页码值,每页显示条数;作用于最近执行sql语句,最好连着写
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    //查询详细信息
    @Override
    public Orders findById(String id) throws Exception {
        return ordersDao.findById(id);
    }
}
