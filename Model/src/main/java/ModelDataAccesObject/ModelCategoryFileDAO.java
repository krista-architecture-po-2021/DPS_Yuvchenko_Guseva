package ModelDataAccesObject;


import ModelDataClass.DataCategory;
import ModelDataClass.NoDataFileException;
import Providers.IProvider;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModelCategoryFileDAO implements IModelCategoryDAO{
    private IProvider provider;

    public ModelCategoryFileDAO(IProvider provider){
        this.provider = provider;
    }

    /**
     * Возвращает лист категорий
     * @return
     */
    public ArrayList<DataCategory> getListCategory() {
        try {
            return provider.getList("CATEGORY");
        } catch (NoDataFileException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addCategory(DataCategory dataCategory) {
        provider.save("CATEGORY "+dataCategory.category,dataCategory);
    }

}
