package ModelPackage;

import ModelDataAccesObject.IModelCategoryDAO;
import ModelDataAccesObject.IModelNewsDAO;
import ModelDataAccesObject.ModelCategoryFileDAO;
import ModelDataAccesObject.ModelNewsDAO;
import Providers.IProvider;
import Providers.ProviderFiles;
import Providers.ProviderMas;

public class ModelFile extends Factory{
    IProvider provider;

    @Override
    public IModelCategoryDAO getModelCategoryDAO() {
        provider = new ProviderFiles();
        return new ModelCategoryFileDAO(provider);
    }

    @Override
    public IModelNewsDAO getModelNewsDAO() {
        provider = new ProviderFiles();
        return new ModelNewsDAO(provider);
    }
}
