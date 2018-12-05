package cn.itcast.ssm.service;

import cn.itcast.ssm.domain.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll() throws Exception;

    //添加产品
    void save(Product product) throws Exception;
}
