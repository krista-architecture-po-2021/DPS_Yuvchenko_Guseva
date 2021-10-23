package ModelDataAccesObject;

import ModelDataClass.DataNews;
import ModelDataClass.NoDataFileException;
import Providers.IProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;

@Component
public class ModelNewsDAO implements IModelNewsDAO {

    private IProvider provider;


    public ModelNewsDAO(IProvider provider) {
        this.provider = provider;
    }


    public DataNews getDataNews(String filename) throws NoDataFileException {
        String entityName = "NEWS " + filename;
        return provider.getDO(entityName);
    }

    public ArrayList<DataNews> getListNews() {
        try {
            return provider.getList("NEWS");
        } catch (NoDataFileException e) {
            //e.printStackTrace();
        }
        return null;
    }


    /**
     * сохраняет одну новость
     *
     * @param dataNews
     */
    public void saveNews(DataNews dataNews) {
        provider.save("NEWS "+dataNews.name,dataNews);
    }

    /**
     * удаляет файлы для формирования новости
     *
     * @param name
     */
    public void deleteDataNews(String name) {
        provider.update_delite("NEWS "+name,null);
    }

    public void updateNews(String oldName, DataNews dataNews) {
        provider.update_delite("NEWS "+oldName,dataNews);
    }

}
