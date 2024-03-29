package com.foodbox.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.bean.FoodItem;
import com.foodbox.service.FoodItemService;

@RestController
@RequestMapping("/foodbox")
public class MyController {

	@Autowired
	private FoodItemService foodItemService;
	
//	@Autowired
//	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<?> saveFoodItem(@RequestBody FoodItem item){
		FoodItem save=foodItemService.saveFoodItem(item);
		if(save!=null) return new ResponseEntity<FoodItem>(save,HttpStatus.OK);
		else return new ResponseEntity<String>("Item could not be saved!!",HttpStatus.BAD_REQUEST);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FoodItem> getFoodItemById(@PathVariable Integer id) {
		return new ResponseEntity<FoodItem>( foodItemService.getFoodItemById(id),HttpStatus.OK);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<FoodItem> getFoodItemByName(@PathVariable String name) {
		return new ResponseEntity<FoodItem>( foodItemService.getFoodItemByName(name),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllFoodItems(){
		List<FoodItem> foodItems=foodItemService.getAllFoodItems();
		if(foodItems==null||foodItems.isEmpty())
			return new ResponseEntity<String>("No Food Item present Currently!!",HttpStatus.NO_CONTENT);
		else return new ResponseEntity<List<FoodItem>>(foodItems,HttpStatus.OK);
	}
	
	@GetMapping("/{cuisine}")
	public ResponseEntity<?> getFoodByCuisine(@PathVariable String cuisine){
		List<FoodItem> items=foodItemService.getFoodByCuisine(cuisine);
		if(items.isEmpty()||items==null)
			return new ResponseEntity<String>("No FoodItems In this Cuisine",HttpStatus.BAD_REQUEST);
		else return new ResponseEntity<List<FoodItem>>(items,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getFoodCategorisedByCuisine(){
		Map<String, List<FoodItem>> map=foodItemService.getFoodCategorisedByCuisine();
		if(map.isEmpty()||map==null)
			return new ResponseEntity<String>("No cuisine based food",HttpStatus.BAD_REQUEST);
		else return new ResponseEntity<Map<String, List<FoodItem>>>(map,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFoodItem(@PathVariable Integer id){
		if(foodItemService.deleteFoodItem(id))
			return new ResponseEntity<String>("FoodItem Successfully deleted!!",HttpStatus.OK);
		else return new ResponseEntity<String>("Item could not be deleted",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/UpdateFoodItemStatus/{id}")
	public ResponseEntity<String> enableDisableFoodItem(@PathVariable Integer id, @RequestBody FoodItem item) {
		if(foodItemService.enableDisablefoodItem(id,item.isEnabled()))
		return new ResponseEntity<String>("updated successfully",HttpStatus.OK);
		else return new ResponseEntity<String>("Unable to update food item",HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/UpdateFoodName/{id}")
	public ResponseEntity<String> UpdateFoodName(@PathVariable Integer id, @RequestBody FoodItem item) {
		if(foodItemService.updateFoodName(id,item.getName()))
		return new ResponseEntity<String>("updated successfully",HttpStatus.OK);
		else return new ResponseEntity<String>("Unable to update food item",HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/UpdateFoodCategory/{id}")
	public ResponseEntity<String> UpdateFoodCategory(@PathVariable Integer id, @RequestBody FoodItem item) {
		if(foodItemService.updateFoodCategory(id,item.getCategory()))
		return new ResponseEntity<String>("updated successfully",HttpStatus.OK);
		else return new ResponseEntity<String>("Unable to update food item",HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/UpdateFoodDescription/{id}")
	public ResponseEntity<String> UpdateFoodDescription(@PathVariable Integer id, @RequestBody FoodItem item) {
		if(foodItemService.updateFoodDescription(id,item.getDescription()))
		return new ResponseEntity<String>("updated successfully",HttpStatus.OK);
		else return new ResponseEntity<String>("Unable to update food item",HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/UpdateFoodPrice/{id}")
	public ResponseEntity<String> UpdateFoodPrice(@PathVariable Integer id, @RequestBody FoodItem item) {
		if(foodItemService.updateFoodPrice(id,item.getPrice()))
		return new ResponseEntity<String>("updated successfully",HttpStatus.OK);
		else return new ResponseEntity<String>("Unable to update food item",HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/UpdateFoodDiscount/{id}")
	public ResponseEntity<String> UpdateFoodDiscount(@PathVariable Integer id, @RequestBody FoodItem item) {
		if(foodItemService.updateFoodDiscount(id,item.getDiscount()))
		return new ResponseEntity<String>("updated successfully",HttpStatus.OK);
		else return new ResponseEntity<String>("Unable to update food item",HttpStatus.BAD_REQUEST);
	}

//	@PostMapping
//	public ResponseEntity<?> placeOrder(@RequestBody Order order){
//		Order o=orderService.save(order);
//		if(o==null)
//			return new ResponseEntity<String>("Order could'nt be confirmed",HttpStatus.OK);
//		else return new ResponseEntity<Order>(o,HttpStatus.OK);
//	}
}
