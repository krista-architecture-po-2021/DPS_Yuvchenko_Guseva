import java.util.List;

public class AbstractDecor implements IAllNews{
    private IAllNews allNews;
    public AbstractDecor(IAllNews iAllNews){
        this.allNews = iAllNews;
    }
    @Override
    public List<News> getList() {
        return allNews.getList();
    }

    @Override
    public News getNews(String name) {
        return allNews.getNews(name);
    }

    @Override
    public void add(News news) {
        allNews.add(news);
    }

    @Override
    public void delete(String name) {
        allNews.delete(name);
    }

    @Override
    public void updateNews(String oldName, News News) {
        allNews.updateNews(oldName, News);
    }
}
