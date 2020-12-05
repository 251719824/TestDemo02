package cn.tedu.rabbitmq.m5_topic;

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
		f.setHost("192.168.64.140");
		f.setUsername("admin");
		f.setPassword("admin");
		//f.setVirtualHost("z");	//用自己主机服务器就不用
		
		Channel c = f.newConnection().createChannel();
		//定义交换机
		c.exchangeDeclare("topic_logs", BuiltinExchangeType.TOPIC);
		String queue = c.queueDeclare().getQueue();
		System.out.println("输入绑定建.用空格隔开:");
		String s= new Scanner(System.in).nextLine();
		String[] a = s.split("\\s+");
		for (String key : a) {
			c.queueBind(queue, "topic_logs", key);
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
