package com.shengsiyuan.netty.thrift.server;

import com.shengsiyuan.netty.thrift.PrideService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

public class Server {
    public static void main(String[] args) throws TTransportException {
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        THsHaServer.Args ta = new THsHaServer.Args(socket).minWorkerThreads(2)
                .maxWorkerThreads(4);
        PrideService.Processor<PrideServiceImpl> processor = new PrideService.Processor<>(new PrideServiceImpl());
        ta.protocolFactory(new TCompactProtocol.Factory());
        ta.transportFactory(new TFramedTransport.Factory());
        ta.processorFactory(new TProcessorFactory(processor));
        TServer server = new THsHaServer(ta);
        System.out.println("thrift server started");
        server.serve();
    }
}
