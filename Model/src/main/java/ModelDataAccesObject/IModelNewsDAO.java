package ModelDataAccesObject;


import ModelDataClass.DataNews;
import ModelDataClass.NoDataFileException;

import java.util.ArrayList;

public interface IModelNewsDAO {
    public DataNews getDataNews(String filename) throws NoDataFileException;

    public ArrayList<DataNews> getListNews();

    /**
     * сохраняет одну новость
     * @param dataNews
     */
    public void saveNews(DataNews dataNews);

    /**
     * удаляет файлы для формирования новости
     * @param name
     */
    public void deleteDataNews(String name);

    public void updateNews(String oldName, DataNews dataNews);
}
