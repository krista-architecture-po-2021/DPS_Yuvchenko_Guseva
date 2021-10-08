package ModelPackage;

import ModelDataAccesObject.DataNewsDAO;
import ModelDataClass.DataNews;
import ModelDataClass.NoNewsException;

import java.util.ArrayList;

public interface IModelNewsDAO {
    public DataNews getDataNews(String filename) throws NoNewsException;

    public ArrayList<DataNews> getListNews();

    /**
     * сохраняет одну новость
     * @param dataNews
     */
    public void saveNewsToFiles(DataNews dataNews);

    /**
     * удаляет файлы для формирования новости
     * @param name
     */
    public void deleteDataNews(String name);

    public void updateNameOfNews(String name, String newName);

    public void updateContentOfNews(String name, String newContent);
}
