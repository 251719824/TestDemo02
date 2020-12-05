package com.pd;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pd.pojo.PdOrder;
import com.pd.service.OrderService;

@Component
public class OrderConsumer {
	
	@Autowired
	OrderService orderServ;
	
	@RabbitListener(queues = "orderQueue")
	public void getOrder(PdOrder pdOrder) throws Exception {
		System.out.println("消费者");
		System.out.println(pdOrder.toString());
		try {
			orderServ.saveOrder(pdOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	
}
