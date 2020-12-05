package cn.tedu.rabbitmq.m2_work;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.Channel;
import com.rabbitmq.client.ConnectionFactory;

public class Test01 {
	public static void main(String[] args) throws Exception{
		ConnectionFactory f = new ConnectionFactory();
		f.setHost("192.168.64.140");
		f.setUsername("admin");
		f.setPassword("admin");
	com.rabbitmq.client.Channel c = f.newConnection().createChannel();
	
	//定义队列
	c.queueDeclare("helloworld", false, false, false, null);//是否持久化队列.是否独占,是否是自动删除,其他属性
	while (true) {
		System.out.println("输入:");
		String msg=new Scanner(System.in).nextLine();
		c.basicPublish("", "helloworld", null, msg.getBytes());
		
	}	
	//c.close();	
	}
}
