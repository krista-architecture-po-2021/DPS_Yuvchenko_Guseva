package ModelPackage;

import ModelDataAccesObject.IModelCategoryDAO;
import ModelDataAccesObject.IModelNewsDAO;

public abstract class Factory {

    public static Factory getModel(String nameModel) {
        if ("ModelFile".equals(nameModel)) return new ModelFile();
        return new ModelMas();
    }

    public abstract IModelCategoryDAO getModelCategoryDAO();

    public abstract IModelNewsDAO getModelNewsDAO();
}
