import ModelDataClass.DataNews;

import java.util.List;


public class FacadeService {
    private IAllNews serviceNews;
    private IAllCategory serviceCategory;
    private String modelservise;

    /**
     * если код <= 1 - только от нужных авторов
     * если код <= 2 - только новые
     * если код <= 3 - только позитивные
     * @param modelservise
     * @param kod
     */
    public FacadeService(String modelservise,int kod) {
        this.modelservise = modelservise;
          serviceNews = new AllNews(modelservise);

          if(kod>0) serviceNews = new DecorAuthors(serviceNews);
          if(kod>1) serviceNews = new DecorDataTime(serviceNews);
          if(kod>2) serviceNews = new DecorSedWords(serviceNews);

          serviceCategory = new AllCategory(modelservise);
    }

    public List<News> getListNews() {
        return serviceNews.getList();
    }

    public News getNews(String name) {
        return serviceNews.getNews(name);

    }

    public void addNews(News news) {
        serviceNews.add(news);
    }

    public void updateNews(String oldName, News news) {
        serviceNews.updateNews(oldName, news);
    }

    public void deleteNews(String name) {
        serviceNews.delete(name);
    }

    public List<String> getServiceCategory() {
        return serviceCategory.getList();
    }

    public Category getCategory(String name) {
        return serviceCategory.getCategory(name);
    }

    public void addListCategory(String name) {
        serviceCategory.addCategory(name);
    }

    public void updateCategory(String name, String newName) {
        serviceCategory.updateNameCategory(name, newName);
    }

    public void deleteCategory(String name) {
        serviceCategory.deleteCategory(name);
    }
}
