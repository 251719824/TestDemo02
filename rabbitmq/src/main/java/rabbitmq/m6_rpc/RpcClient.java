package rabbitmq.m6_rpc;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class RpcClient {
	
	static BlockingQueue<String> q = new ArrayBlockingQueue<>(10);
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		System.out.println("求第几个菲波那切数");
		int n = new Scanner(System.in).nextInt();
		f(n);
		
	}
	private static long f(int n) throws Exception{
		//1.发送调用数据
		//2.接收执行结果
		ConnectionFactory f = new ConnectionFactory();
		f.setHost("192.168.64.140");	//www.wht6.cn
		f.setPort(5672);
		f.setUsername("admin");
		f.setPassword("admin");
		//f.setVirtualHost("x");
		
		Channel c = f.newConnection().createChannel();
		//发送数据:n,返回队列名称,关联id
		c.queueDeclare("rpc_queue", false, false, false, null);
		String queue = c.queueDeclare().getQueue();
		String cid = UUID.randomUUID().toString();
		BasicProperties props=	new BasicProperties.Builder()
			.replyTo(queue)
			.correlationId(queue)
			.build();
		
		c.basicPublish("", "rpc_queue", props, (""+n).getBytes());
		
		System.out.println("已发送调用");
		System.out.println("主线程不必等待,继续执行其他运算");
		System.out.println("直到需要结果时,再取结果");
		//接收结果
		DeliverCallback deliverCallback = new DeliverCallback() {
			
			@Override
			public void handle(String consumerTag, Delivery message) throws IOException {
				//处理从服务器返回的计算结果
				//把计算结果放入一个集合
				String msg = new String(message.getBody());
				String cid2 = message.getProperties().getReplyTo();
				if (cid.equals(cid2)) {
					q.offer(msg);
				}
				
				
			}
		};
		CancelCallback cancelCallback = new CancelCallback() {
			
			@Override
			public void handle(String consumerTag) throws IOException {
				// TODO Auto-generated method stub
				
			}
		};
		
		
		
		//接收结果
		c.basicConsume(queue, true,deliverCallback,cancelCallback);
		
		//从主线程获取结果
	return Long.parseLong(q.take());	
		
		
		
		
	
	}
	
}
