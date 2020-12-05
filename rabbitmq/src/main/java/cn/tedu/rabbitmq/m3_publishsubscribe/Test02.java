package cn.tedu.rabbitmq.m3_publishsubscribe;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class Test02 {
	public static void main(String[] args) throws Exception {
		
		ConnectionFactory f = new ConnectionFactory();
		f.setHost("192.168.64.140");	//www.wht6.cn
		f.setPort(5672);
		f.setUsername("admin");
		f.setPassword("admin");
		//f.setVirtualHost("x");
		
		Channel c = f.newConnection().createChannel();
		
		//1.定义交换机		2.定义队列	3.绑定
		c.exchangeDeclare("logs", "fanout");
		//服务器随机命名,非持久,排他(独占),自动删除,其它属性
		String queue = c.queueDeclare().getQueue();
		//第三个参数,对于fanout交换机无效
		c.queueBind(queue, "logs", "");
		
		DeliverCallback deliverCallback = new DeliverCallback() {
			public void handle(String consumerTag, Delivery message) throws IOException {
				String msg = new String(message.getBody());
				System.out.println("收到:"+msg);
			}
		};
		CancelCallback cancelCallback = new CancelCallback() {
			public void handle(String consumerTag) throws IOException {
				
			}
		};
		
		//正常从队列接收数据
		c.basicConsume(queue, true, deliverCallback, cancelCallback);
		
	}
	
}
