package Providers;

import ModelDataClass.DataCategory;
import ModelDataClass.DataNews;
import ModelDataClass.NoDataFileException;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

public class ProviderMas implements IProvider{
    private ArrayList<DataNews> dataNewsArrayList = new ArrayList<>();
    private ArrayList<DataCategory> dataCategoryArrayList = new ArrayList<>();

    @Override
    public <T> ArrayList<T> getList(String type) throws NoDataFileException {
        if("NEWS".equals(type.toUpperCase(Locale.ROOT))) return (ArrayList<T>) dataNewsArrayList;
        else return (ArrayList<T>) dataNewsArrayList;
    }

    @Override
    public <T> T getDO(String entityName) throws NoDataFileException {
        String[] masdata = entityName.split(" ");
        if("NEWS".equals(masdata[0].toUpperCase(Locale.ROOT))) {
            for(int i=0;i<dataNewsArrayList.size();i++){
                if(dataNewsArrayList.get(i).name.equals(masdata[1])) return (T) dataNewsArrayList.get(i);
            }
        }
        else for(int i=0;i<dataCategoryArrayList.size();i++){
            if(dataCategoryArrayList.get(i).category.equals(masdata[1])) return (T) dataCategoryArrayList.get(i);
        };
        throw new NoDataFileException();
    }

    @Override
    public <T> void save(String entityName, T dO) {
        String[] masdata = entityName.split(" ");
        if(!check(masdata[0],masdata[1])) {
            if ("NEWS".equals(masdata[0].toUpperCase(Locale.ROOT))) {
                dataNewsArrayList.add((DataNews) dO);
            } else for (int i = 0; i < dataCategoryArrayList.size(); i++) {
                dataCategoryArrayList.add((DataCategory) dO);
            }
        }
    }

    @Override
    public boolean check(String type, String name) {
        if("NEWS".equals(type.toUpperCase(Locale.ROOT))) {
            for(int i=0;i<dataNewsArrayList.size();i++){
                if(dataNewsArrayList.get(i).name.equals(name)) return true;
            }
        }
        else for(int i=0;i<dataCategoryArrayList.size();i++){
            if(dataCategoryArrayList.get(i).category.equals(name)) return true;
        };
        return false;
    }

    @Override
    public <T> void update_delite(String entityName, T dO) {
        String[] masdata = entityName.split(" ");
        if("NEWS".equals(masdata[0].toUpperCase(Locale.ROOT))) {
            for(int i=0;i<dataNewsArrayList.size();i++){
                if(dataNewsArrayList.get(i).name.equals(masdata[1])) {
                    dataNewsArrayList.remove(i);
                    dataNewsArrayList.add((DataNews) dO);
                }
            }
        }
        else for(int i=0;i<dataCategoryArrayList.size();i++){
            if(dataCategoryArrayList.get(i).category.equals(masdata[1])) {
                dataNewsArrayList.remove(i);
                dataCategoryArrayList.add((DataCategory) dO);
            }
        };
    }
}
