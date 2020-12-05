package cn.tedu.rabbitmq.m2_work;

import java.io.IOException;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class Test02 {
	public static void main(String[] args) throws Exception {
		//1.连接
		ConnectionFactory f = new ConnectionFactory();
		f.setHost("192.168.64.140");
		f.setUsername("admin");
		f.setPassword("admin");
		//f.setVirtualHost("z");	//用自己主机服务器就不用

		Channel c = f.newConnection().createChannel();

		//2.定义队列
		c.queueDeclare("helloworld",false,false,false,null);//是否持久队列,是否排他队列,是否自动删除,其它参数属性
		
		//处理消息数据的回调对象
		DeliverCallback deliverCallback = new DeliverCallback() {
			public void handle(String consumerTag, Delivery message) throws IOException {
				byte[] a = message.getBody();
				String msg = new String(a);
				System.out.println("收到:"+msg);
				//"sfsdd.sdfdf.....dffefe...sdfddf....sdfds..."
				for(int i = 0 ; i < msg.length(); i++) {
					if('.' == msg.charAt(i)) {
						try {
							Thread.sleep(1000);
						}catch(InterruptedException e) {
						}
					}
				}
				//发送回执
				c.basicAck(message.getEnvelope().getDeliveryTag(), false);
				
				System.out.println("消息处理结束\n");
			}
		};
		//取消接收时的回调对象
		CancelCallback cancelCallback = new CancelCallback() {
			public void handle(String consumerTag) throws IOException {
			}
		};
		
		
		//3.消费数据
		c.basicConsume("helloworld", false, deliverCallback, cancelCallback);

	}
}
