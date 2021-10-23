import ModelDataClass.DataCategory;
import ModelPackage.Factory;

import java.util.ArrayList;
import java.util.List;

public class SingletonCategory {
    private static List<Category> singleton = null;
    public static Factory modelNews = Factory.getModel("ModelFile");

    private SingletonCategory() {
    }

    public static synchronized List<Category> getInstance() {
        if (singleton == null) {
            singleton = new ArrayList<Category>();
            ArrayList<DataCategory> listCat = modelNews.getModelCategoryDAO().getListCategory();
            for (DataCategory datacat : listCat) {
                singleton.add(new Category(datacat.category));
            }
        }
        return singleton;
    }
}

class SingletonCategory1 {
    private List<Category> listallcategory;
    private static SingletonCategory1 singleton = null;

    public static Factory modelNews = Factory.getModel("ModelFile");

    private SingletonCategory1() {
        ArrayList<DataCategory> listCat = modelNews.getModelCategoryDAO().getListCategory();
        for (DataCategory datacat : listCat) {
            listallcategory.add(new Category(datacat.category));
        }
        listallcategory = new ArrayList<>();
    }

    public static synchronized List<Category> getInstance() {
        if (singleton == null) {
            singleton = new SingletonCategory1();
        }
        return singleton.listallcategory;
    }
}