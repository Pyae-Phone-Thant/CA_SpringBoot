package sg.edu.nus.shoopingcart.interfacemethods;

import java.util.List;

import sg.edu.nus.shoopingcart.model.Category;
//Aurthor Pyae Phone Thant
public interface CategoryInterface {
	public List<Category> findAllCategories();
	public int findCategoryByName(String name);
	public void saveCategory(Category category);
	public long getTotalCategoriesCount();
}
