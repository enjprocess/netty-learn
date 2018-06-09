package com.shengsiyuan.netty.thrift.server;

import com.shengsiyuan.netty.thrift.DaoException;
import com.shengsiyuan.netty.thrift.Pride;
import com.shengsiyuan.netty.thrift.PrideService;
import org.apache.thrift.TException;

public class PrideServiceImpl implements PrideService.Iface {
    @Override
    public Pride getPrideByUsername(String username) throws DaoException, TException {
        Pride pride = new Pride();
        pride.setName("zhangsan");
        pride.setAge(12);
        pride.setAddress("anywhere");
        return pride;
    }

    @Override
    public void savePride(Pride pride) throws DaoException, TException {
        System.out.println("prideServiceImpl...");
        System.out.println(pride.getName());
        System.out.println(pride.getAge());
        System.out.println(pride.getAddress());
    }
}
