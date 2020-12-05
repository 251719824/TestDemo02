package cn.tedu.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Test02 {
	public static void main(String[] args) throws Exception {
		//1.连接
		ConnectionFactory f = new ConnectionFactory();
		f.setHost("192.168.64.140");
		f.setPort(5672);
		f.setUsername("admin");
		f.setPassword("admin");
		Connection con = f.newConnection();
		Channel c = con.createChannel();
		//2.定义队列
		//参数:队列名.是否是持久队列,是否是消费者独占队列,没有消费者是否自动删除,其他的参数属性配置
		c.queueDeclare("helloworld", false, false, false, null);
		System.out.println("等待接收数据");
		//回调对象
		DeliverCallback callback = new DeliverCallback() {
			
			@Override
			public void handle(String consumerTag, Delivery message) throws IOException {
				byte []a = message.getBody();
				String msg = new String(a);
				String thread = Thread.currentThread().getName();
				System.out.println(thread+"收到"+msg);
				for (int i = 0; i < msg.length(); i++) {
					if (msg.charAt(i)=='.') {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
						}
					}
				}
				System.out.println("处理结束");
			}
		};
		CancelCallback cancle = new CancelCallback() {
			
			@Override
			public void handle(String consumerTag) throws IOException {
				
			}
		};
		
		
		//3.从队列接收数据
		c.basicConsume("helloworld", true, callback,cancle);
		
	}
}
