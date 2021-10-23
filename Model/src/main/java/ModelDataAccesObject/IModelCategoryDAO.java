package ModelDataAccesObject;


import ModelDataClass.DataCategory;

import java.util.ArrayList;

public interface IModelCategoryDAO {
    public ArrayList<DataCategory> getListCategory();
    public void addCategory(DataCategory dataCategory);
}
