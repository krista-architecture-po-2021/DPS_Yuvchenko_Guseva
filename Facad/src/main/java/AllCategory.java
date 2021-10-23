import ModelDataClass.DataCategory;
import ModelPackage.Factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AllCategory implements IAllCategory {
    public List<Category> categoryList = SingletonCategory.getInstance();
    //private Factory model = Factory.getModel("ModelFile");
    private Factory model = Factory.getModel("ModelFile");

    public AllCategory(String model){
        this.model = Factory.getModel(model);
    }

    public List getList() {
        return categoryList;
    }

    public Category getCategory(String name) {
        for (Category category : categoryList
        ) {
            if (category.getNameCategory().equals(name)) {
                return category;
            }
        }
        return null;
    }

    @Override
    public void deleteCategory(String name) {
        Iterator iterator = categoryList.iterator();
        while (iterator.hasNext()) {
            Category cat = (Category) iterator.next();
            if (name.equals(cat.getNameCategory())) iterator.remove();
        }
        /*for(int i=0;i<categoryList.size();i++){
            if(categoryList.get(i).getNameCategory().equals(name)){
                categoryList.remove(i);

            }
        }*/
    }

    @Override
    public void updateNameCategory(String name, String newName) {
        for (Category category : categoryList) {
            if (category.getNameCategory().equals(name)) {
                category.setNameCategory(newName);
            }
        }

    }

    public void addCategory(String name) {
        boolean f = true;
        for (Category category : categoryList) {
            if (category.getNameCategory().equals(name)) {
                f = false;
            }
        }
        if (f) {
            categoryList.add(new Category(name));
            model.getModelCategoryDAO().addCategory(new DataCategory(name));
        }

    }
}

class Category {
    private String nameCategory;

    public Category(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}