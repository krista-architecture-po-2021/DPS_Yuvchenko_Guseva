import java.util.HashSet;
import java.util.List;

public class DecorSedWords extends AbstractDecor{
    HashSet<String> badWords;
    public DecorSedWords(IAllNews iAllNews) {
        super(iAllNews);
        badWords = new HashSet<>();
        badWords.add("коронавирус");
        badWords.add("коронавируса");
        badWords.add("коронавирусы");
        badWords.add("коронавирусов");
        badWords.add("ковид");
        badWords.add("SARS-KOV-19");
    }

    private boolean checkKovid(String data){
        boolean f = false;
        String[] masdata = data.split(" ");
        for(String str : masdata){
            if(badWords.contains(str)) f =true;
        }
        return f;
    }

    @Override
    public News getNews(String name) {
        News news = super.getNews(name);
        if(news==null) return null;
        if(checkKovid(news.getContent())) return null;
        else return news;
    }

    @Override
    public List<News> getList() {
        List<News> list = super.getList();
        for(int i=0;i<list.size();i++){
            if(checkKovid(list.get(i).getContent())){
                list.remove(i);
                i--;
            }
        }
        return list;
    }

}
