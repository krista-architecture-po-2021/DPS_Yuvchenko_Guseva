import java.util.ArrayList;
import java.util.List;

public class AllCategory implements IAllCategory{
    public List<Category> categoryList = SingletonCategory.getInstance();

    public List getList() {
        return categoryList;
    }


    public void add(String name) {
        categoryList.add(new Category(name));
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