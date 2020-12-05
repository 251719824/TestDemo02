package cn.tedu.rabbitmq.m3_publishsubscribe;

import java.util.Scanner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

public class Test01 {
	public static void main(String[] args) throws Exception {
		//连接
		ConnectionFactory f = new ConnectionFactory();
		f.setHost("192.168.64.140");
		f.setUsername("admin");
		f.setPassword("admin");
		//f.setVirtualHost("z");	//用自己主机服务器就不用
		
		Channel c = f.newConnection().createChannel();
		
		//定义交换机
		c.exchangeDeclare("logs", "fanout");
		
		//发送数据
		while (true) {
			System.out.println("输入:");
			String msg = new Scanner(System.in).nextLine();
			/*
			 * 第一个参数:
			 * 		空串: 默认的直连交换机(direct)
			 * 第一个参数:
			 * 		对于发布和订阅模式无效
			 */
			c.basicPublish("logs", "", null, msg.getBytes());
			
			
		}
		
	}
}
