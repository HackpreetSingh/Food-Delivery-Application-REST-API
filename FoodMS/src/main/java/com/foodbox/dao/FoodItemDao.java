package com.foodbox.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.foodbox.bean.FoodItem;

public interface FoodItemDao extends JpaRepository<FoodItem, Integer> {

	@Query("Select f from FoodItem f where f.category=?1")
	public List<FoodItem> findByCuisine(String cuisine);

	@Query("Select f from FoodItem f where f.name=?1")
	public FoodItem findByName(String name);

	@Transactional
	@Modifying
	@Query("Update FoodItem f set f.enabled=?2 where f.id=?1")
	public Integer updateFoodStatus(Integer id, Boolean enabled);

	@Transactional
	@Modifying
	@Query("Update FoodItem f set f.name=?2 where f.id=?1")
	public Integer updateFoodName(Integer id, String name);
	
	@Transactional
	@Modifying
	@Query("Update FoodItem f set f.category=?2 where f.id=?1")
	public Integer updateFoodCategory(Integer id, String category);
	
	@Transactional
	@Modifying
	@Query("Update FoodItem f set f.description=?2 where f.id=?1")
	public Integer updateFoodDescription(Integer id, String description);
	
	@Transactional
	@Modifying
	@Query("Update FoodItem f set f.price=?2 where f.id=?1")
	public Integer updateFoodPrice(Integer id, Double price);
	
	@Transactional
	@Modifying
	@Query("Update FoodItem f set f.discount=?2 where f.id=?1")
	public Integer updateFoodDiscount(Integer id, Double discount);
	
}
