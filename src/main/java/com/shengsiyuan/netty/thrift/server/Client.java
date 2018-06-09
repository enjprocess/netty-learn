package com.shengsiyuan.netty.thrift.server;

import com.shengsiyuan.netty.thrift.Pride;
import com.shengsiyuan.netty.thrift.PrideService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class Client {
    public static void main(String[] args) {
        TTransport transPort = new TFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(transPort);
        PrideService.Client client = new PrideService.Client(protocol);
        try {
            transPort.open();
            Pride pride = client.getPrideByUsername("zhangsan");
            System.out.println("client...");
            System.out.println(pride.getName());
            System.out.println(pride.getAge());
            System.out.println(pride.getAddress());
            Pride savePride = new Pride();
            savePride.setName("lisi");
            savePride.setAddress("world");
            savePride.setAge(14);
            client.savePride(savePride);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage() ,ex);
        } finally {
            transPort.close();
        }
    }
}
