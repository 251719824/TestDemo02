package rabbitmq.m6_rpc;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestABQ {
	
	static BlockingQueue<Double> q = new ArrayBlockingQueue<>(10);
	
	public static void main(String[] args) {
			new Thread() {
				public void run() {
					System.out.println("\n 线程 1 - 按回车放入数据");
					new Scanner(System.in).nextLine();
					double d = Math.random();
						q.offer(d);//放入数据
					System.out.println("放入数据:"+d);
				};
			}.start();
			
			new Thread() {
				public void run() {
					try {
						System.out.println("线程 2 -正在获取数据");
						Double d = q.take();
						System.out.println("线程 2 -已经获取数据:"+d);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				};
			}.start();
	}
}
