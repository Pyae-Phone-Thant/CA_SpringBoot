package sg.edu.nus.shoopingcart.repository;

//Author Pyae Phone Thant

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.shoopingcart.model.Brand;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
	@Query("select b from Brand as b where b.status=true")
	List<Brand> findAllBrands();
	
	@Query("select count(*) from Brand as b where b.brand_name=:name and b.status=true")
	int findBrandByName(@Param("name")String name);
}
