import java.util.List;

public class AbstractDecor implements IAllNews{
    IAllNews allNews;
    
    @Override
    public List<News> getList() {
        return null;
    }

    @Override
    public News getNews(String name) {
        return null;
    }

    @Override
    public void add(News news) {

    }

    @Override
    public void delete(String name) {

    }

    @Override
    public void updateNews(String oldName, News News) {

    }
}
