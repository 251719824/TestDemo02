package cn.tedu.sp09.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;

@FeignClient(name="item-service", fallback = ItemFeignClientFB.class)
public interface ItemFeignClient {
	
	//调用后台商品服务,调用商品服务的路径:"/{orderId}"
	@GetMapping("/{orderId}")
	JsonResult<List<Item>> getItems(@PathVariable String orderId);
	
	@PostMapping("/decreaseNumber")
	JsonResult<?> decreaseNumber(@RequestBody List<Item> items);
	
}
