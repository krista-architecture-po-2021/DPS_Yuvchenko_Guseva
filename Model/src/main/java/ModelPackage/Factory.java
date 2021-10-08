package ModelPackage;

import ModelDataAccesObject.DataCategoryDAO;
import ModelDataAccesObject.DataNewsDAO;
import ModelDataClass.DataNews;
import ModelDataClass.NoNewsException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Factory {

    public static Factory getModel(String nameModel) {
        if ("ModelFile".equals(nameModel)) return new ModelFile();
        return new ModelJson();
    }

    public abstract IModelCategoryDAO getModelCategoryDAO();

    public abstract IModelNewsDAO getModelNewsDAO();
}
