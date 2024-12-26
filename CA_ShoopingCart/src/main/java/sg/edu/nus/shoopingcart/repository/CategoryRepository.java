package sg.edu.nus.shoopingcart.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.shoopingcart.model.Category;
//Author Pyae Phone Thant
public interface CategoryRepository extends JpaRepository<Category,Integer> {
	@Query("select c from Category as c where c.status=true")
	List<Category> findAllCategories();
	
	@Query("select count(*) from Category as c where c.category_name=:name and c.status=true")
	int findCategoryByName(@Param("name")String name);
}
