package sg.edu.nus.shoopingcart.service;
//Author Pyae Phone Thant

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.shoopingcart.interfacemethods.CategoryInterface;
import sg.edu.nus.shoopingcart.model.Category;
import sg.edu.nus.shoopingcart.repository.CategoryRepository;

@Service
public class CategoryImplementation implements CategoryInterface {
	@Autowired
	private CategoryRepository categoryRepo;
	
	public List<Category> findAllCategories(){
		return categoryRepo.findAllCategories();
	}
	public int findCategoryByName(String name) {
		return categoryRepo.findCategoryByName(name);
	}
	public void saveCategory(Category category) {
		categoryRepo.save(category);
	}
	public long getTotalCategoriesCount() {
		return categoryRepo.count();
	}
}
