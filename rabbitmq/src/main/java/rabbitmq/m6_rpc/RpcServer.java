package rabbitmq.m6_rpc;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class RpcServer {
	public static void main(String[] args) throws Exception{
		//1.接收用户端的调用数据,n值,返回队列名,关联id
		//2.执行运算求出斐波那契数
		//3.发挥计算结果,要携带关联id
		ConnectionFactory f = new ConnectionFactory();
		f.setHost("192.168.64.140");	//www.wht6.cn
		f.setPort(5672);
		f.setUsername("admin");
		f.setPassword("admin");
		//f.setVirtualHost("x");
		
		Channel c = f.newConnection().createChannel();
		
		//定义调用队列
		c.queueDeclare("rpc_queue", false, false, false, null);
		
		DeliverCallback deliverCallback= new DeliverCallback() {
			
			@Override
			public void handle(String consumerTag, Delivery message) throws IOException {
				
				//收到回调函数,n值,返回队列名,关联id
				String msg = new String(message.getBody());
				String replyTo = message.getProperties().getReplyTo();
				
				String cid = message.getProperties().getCorrelationId();
				System.out.println(msg+","+replyTo+","+cid);
				//执行运算
				long result = fbnq(Integer.parseInt(msg));
				
				//发回计算结果,向repliyTo队列发送,携带cid
				
				 BasicProperties props= new BasicProperties.Builder()
				 .correlationId(cid)
				 .build();
				c.basicPublish("", replyTo, props, (""+result).getBytes());
				System.out.println("已向客户端发回结果");
				
				
			}
		};
		CancelCallback cancelCallback = new CancelCallback() {
			
			@Override
			public void handle(String consumerTag) throws IOException {
				// TODO Auto-generated method stub
				
			}
		};
		
		
		//从rpc_queue消费数据
		c.basicConsume("rpc_queue", true,deliverCallback,cancelCallback);
		
		
		
	}
	/**
	 * 1.计算斐波那契数的服务
	 * 接收的参数是求第几个菲波那切数
	 * 
	 */
	static long fbnq(int n) {
		if (n==1|| n==2) {
			
			return 1;
		}
		return fbnq(n-1)+fbnq(n-2);
		
	}
	
}
