import ModelDataClass.DataNews;

import java.util.List;

public interface IAllNews {
    public List<News> getList();

    public News getNews(String name);

    public void add(News news);

    public void delete(String name);

    public void updateNews(String oldName, News News);
}
