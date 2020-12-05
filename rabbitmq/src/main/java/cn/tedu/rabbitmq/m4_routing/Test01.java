package cn.tedu.rabbitmq.m4_routing;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

public class Test01 {
	public static void main(String[] args) throws Exception {
		ConnectionFactory f = new ConnectionFactory();
		f.setHost("192.168.64.140");
		f.setUsername("admin");
		f.setPassword("admin");
		//f.setVirtualHost("z");	//用自己主机服务器就不用
		
		Channel c = f.newConnection().createChannel();
		
		//定义交换机
		c.exchangeDeclare("direct_logs", BuiltinExchangeType.DIRECT);
		
		while (true) {
			System.out.println("输入消息:");
			
			String msg = new Scanner(System.in).nextLine();
			System.out.println("输入路由键:");
			
			String key= new Scanner(System.in).nextLine();
			
			//第二个参数时路由键.用路由键做关键词匹配,决定吧信息发到哪个队列
			c.basicPublish("direct_logs", key, null, msg.getBytes());
			
		}
		
		
		
		
		
		
	}
}
