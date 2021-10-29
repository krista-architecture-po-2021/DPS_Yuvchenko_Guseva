import com.google.gson.Gson;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class API_2_Author {
    static final String model = "ModelFile";
    static final boolean[] flags = {false, false, false};

    public static synchronized Answer_Rest deleteNews(String name) {
        IOtoFacad io = new IOtoFacad(model,flags);
        FacadeService fs = new FacadeService(io);
        fs.deleteNews(name);
        boolean f = (fs.getNews(name) == null) ? true : false;
        return new Answer_Rest("boolean",Boolean.toString(f));
    }

    public static synchronized Answer_Rest deleteCategory(String name) {
        FacadeService fs = new FacadeService(new IOtoFacad(model,flags));
        fs.deleteCategory(name);
        boolean f = (fs.getCategory(name) == null) ? true : false;
        return new Answer_Rest("boolean",Boolean.toString(f));
    }

    public static synchronized Answer_Rest getListNews(String NameAuthor) {
        List<News> list = new FacadeService(new IOtoFacad(model,flags)).getListNews();
        for(int i=0;i<list.size();i++){
            if(!list.get(i).getAuthor().equals(NameAuthor)){
                list.remove(i);
                i--;
            }
        }
        Gson g = new Gson();
        String str = g.toJson(list);
        return new Answer_Rest("List<News>",str);
    }

    public static Answer_Rest addNews(News news) {
        FacadeService serviceOfNews = new FacadeService(new IOtoFacad(model,flags));
        int k = serviceOfNews.getListNews().size();
        serviceOfNews.addNews(news);
        //особенность реализации и тестирования файлов
        boolean f = (k == serviceOfNews.getListNews().size()) ? true : false;
        return new Answer_Rest("boolean", Boolean.toString(f));
    }

}
