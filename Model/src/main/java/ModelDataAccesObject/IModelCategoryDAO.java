package ModelPackage;

import ModelDataAccesObject.DataCategoryDAO;
import ModelDataClass.DataCategory;

import java.util.ArrayList;

public interface IModelCategoryDAO {
    public ArrayList<DataCategory> getListCategory();
}
