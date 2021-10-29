import java.util.List;


public class FacadeService {
    private IAllNews serviceNews;
    private IAllCategory serviceCategory;
    private String modelservise;

    /**
     * в массиве передаётся 3 элемента
     * 0 элемент - вкл фильтрацию по авторам
     * 1 элемент - вкл фильтрацию по актуальности (времени)
     * 2 элемент - вкл фильтр плохих снов
     * @param io
     */
    public FacadeService(IOtoFacad io) {
        this.modelservise = modelservise;
          serviceNews = new AllNews(modelservise);

          if(io.flags[0]) serviceNews = new DecorAuthors(serviceNews);
          if(io.flags[1]) serviceNews = new DecorDataTime(serviceNews);
          if(io.flags[2]) serviceNews = new DecorSadWords(serviceNews);

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
