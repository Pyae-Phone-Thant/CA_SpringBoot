package sg.edu.nus.shoopingcart.interfacemethods;

import java.util.List;

import sg.edu.nus.shoopingcart.model.Brand;
//Author Pyae Phone Thant
public interface BrandInterface {
	public List<Brand> findAllBrands();
	public int findBrandByName(String name);
	public void saveBrand(Brand brand);
	public long getTotalBrandsCount();
}
