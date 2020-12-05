package cn.tedu.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Test01 {

	public static void main(String[] args) throws Exception {
		//1.连接
		ConnectionFactory f = new ConnectionFactory();
		f.setHost("192.168.64.140"); //www.wht6.cn
		f.setPort(5672);
		f.setUsername("admin");
		f.setPassword("admin");
		
		Connection con = f.newConnection();
		Channel c = con.createChannel();
		//2.向服务器发送指令,要求服务器准备好对列:HelloWord
		//定义对列
		c.queueDeclare("helloworld", false, false, false, null);
		
		//3.向指定对列发送数据
		c.basicPublish("", "helloworld", null, ("helloworld"+System.currentTimeMillis()).getBytes());
		System.out.println("消息已发送");
		c.close();
		con.close();
		
	}

}
