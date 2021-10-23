import java.util.List;

public class DecorAuthors extends AbstractDecor{
    public DecorAuthors(IAllNews iAllNews) {
        super(iAllNews);
    }

    @Override
    public News getNews(String name) {
        News news = super.getNews(name);
        if(news==null) return null;
        if(news.getAuthor().equals("МАКАР")) return null;
        else return news;
    }

    @Override
    public List<News> getList() {
        List<News> list = super.getList();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getAuthor().equals("МАКАР")){
                list.remove(i);
                i--;
            }
        }
        return list;
    }
}
