import ModelDataClass.NoNewsException;

import java.util.List;

public interface IAllNews {
    public List<News> getList();

    public News getNews(String name) throws NoNewsException;

    public void add(News news);

    public void delete(String name);

    public void delete(News news);
}
