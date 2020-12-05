package cn.tedu.sp09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp09.client.ItemFeignClient;
import cn.tedu.sp09.client.OrderFeignClient;
import cn.tedu.sp09.client.UserFeignClient;
import cn.tedu.web.util.JsonResult;

@RestController
public class FeignController {
	@Autowired
	private ItemFeignClient  itemClient;
	
	@Autowired
	private OrderFeignClient orderClient;
	
	@Autowired
	private UserFeignClient userClient;
	
	@GetMapping("/item-service/{orderId}")
	public JsonResult<List<Item>> getItems(@PathVariable String orderId) {
		return itemClient.getItems(orderId);
	}

	@PostMapping("/item-service/decreaseNumber")
	public JsonResult decreaseNumber(@RequestBody List<Item> items) {
		return itemClient.decreaseNumber(items);
	}
	///////////////////////////////////////////////
	@GetMapping("/user-service/{userId}")
	public JsonResult<User> getUser(@PathVariable Integer userId){
		return userClient.getUser(userId);
	}
	
	
	@GetMapping("/user-service/{userId}/score")
	public JsonResult<User> addScore(@PathVariable Integer userId,Integer score){
		
		return userClient.addScore(userId, score);
	}
	
	
	/////////////////////////////////////////////
	
	@GetMapping("/order-service/{orderId}")
	public JsonResult<Order> getOrder(@PathVariable String orderId){
		return orderClient.getOrder(orderId);
	}
	
	@GetMapping("/order-service")
	public JsonResult<Order> addOrder(){
		
		return orderClient.addOrder();
	}
 	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
