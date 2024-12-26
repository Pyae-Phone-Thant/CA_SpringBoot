package sg.edu.nus.shoopingcart.service;

//Author Pyae Phone Thant
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.shoopingcart.interfacemethods.BrandInterface;
import sg.edu.nus.shoopingcart.model.Brand;
import sg.edu.nus.shoopingcart.repository.BrandRepository;

@Service
public class BrandImplementation implements BrandInterface {
	@Autowired
	private BrandRepository brandRepo;
	
	public List<Brand> findAllBrands(){
		return brandRepo.findAllBrands();
	}
	public int findBrandByName(String name) {
		return brandRepo.findBrandByName(name);
	}
	public void saveBrand(Brand brand) {
		brandRepo.save(brand);
	}
	public long getTotalBrandsCount() {
		return brandRepo.count();
	}
	public void test() {
		
	}
}
