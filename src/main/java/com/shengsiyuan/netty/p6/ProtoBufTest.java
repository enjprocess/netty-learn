package com.shengsiyuan.netty.p6;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Student.Person person = Student.Person.newBuilder().setName("zhangsan")
                .setAddress("shaoxing")
                .setAge(11).build();
        byte[] bytes = person.toByteArray();
        Student.Person newPerson = Student.Person.parseFrom(bytes);
        System.out.println(newPerson);

    }
}
