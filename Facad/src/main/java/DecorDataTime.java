import java.util.List;

public class DecorDataTime extends AbstractDecor{
    public DecorDataTime(IAllNews iAllNews) {
        super(iAllNews);
    }

    @Override
    public News getNews(String name) {
        News news = super.getNews(name);
        if(news==null) return null;
        String[] mastime = news.getDatatime().split(",");//по "." ну ну хочет, почему ?
        if(Integer.parseInt(mastime[2])<2021) return null;
        else return news;
    }

    @Override
    public List<News> getList() {
        List<News> list = super.getList();
        for(int i=0;i<list.size();i++){
            String[] mastime = list.get(i).getDatatime().split(",");
            if(Integer.parseInt(mastime[2])<2021){
                list.remove(i);
                i--;
            }
        }
        return list;
    }

}
