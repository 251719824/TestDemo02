package cn.tedu.rabbitmq.m4_routing;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class Test02 {
	public static void main(String[] args) throws Exception{
		ConnectionFactory f = new ConnectionFactory();
		f.setHost("192.168.64.140");	//www.wht6.cn
		f.setPort(5672);
		f.setUsername("admin");
		f.setPassword("admin");
		//f.setVirtualHost("x");
		
		Channel c = f.newConnection().createChannel();
		//1.定义交换机,2.定义随机队列.3.绑定
		
		c.exchangeDeclare("direct_logs", BuiltinExchangeType.DIRECT);
		String queue = c.queueDeclare().getQueue();
		System.out.println("输入绑定建.用空格隔开:");
		String s =new Scanner(System.in).nextLine();
		String[] a = s.split("\\s+");
		for (String key : a) {
			c.queueBind(queue, "direct_logs", key);
		}
		DeliverCallback deliverCallback=	new DeliverCallback() {
			
			@Override
			public void handle(String consumerTag, Delivery message) throws IOException {
				String msg = new String(message.getBody());
				String key = message.getEnvelope().getRoutingKey();
				System.out.println("收到:"+key+"-"+msg);
				
			}
		};
		CancelCallback cancelCallback= new CancelCallback() {
			
			@Override
			public void handle(String consumerTag) throws IOException {
				// TODO Auto-generated method stub
				
			}
		};
		
		
		
		//正常接收消费数据
		c.basicConsume(queue, true,deliverCallback,cancelCallback);
		
		
		
	}
}
