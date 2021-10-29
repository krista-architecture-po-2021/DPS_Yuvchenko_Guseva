import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class  API_1_Admin {
    static final String model = "ModelFile";
    static final boolean[] flags = {false,false,false};

    public static synchronized Answer_Rest deleteNews(String name) {
        FacadeService fs = new FacadeService(new IOtoFacad(model,flags));
        fs.deleteNews(name);
        boolean flag = ( fs.getNews(name) == null ) ? true: false;
        return new Answer_Rest("boolean", Boolean.toString(flag));
    }

    public static synchronized Answer_Rest getListNews() {
        Gson g = new Gson();
        List<News> list = new FacadeService(new IOtoFacad(model,flags)).getListNews();
        String str = null;
        for(News news : list){
            str += g.toJson(news);
        }
        return new Answer_Rest("List<News>",str);
    }

    public static synchronized Answer_Rest deleteCategory(String name) {
        FacadeService fs = new FacadeService(new IOtoFacad(model,flags));
        fs.deleteCategory(name);
        boolean f = ( fs.getCategory(name) == null ) ? true: false;
        return new Answer_Rest("boolean",Boolean.toString(f));
    }

    public static synchronized Answer_Rest addCategory(String newCategory) {
        FacadeService serviceOfNews = new FacadeService(new IOtoFacad(model,flags));
        serviceOfNews.deleteCategory(newCategory);
        int k = serviceOfNews.getServiceCategory().size();
        serviceOfNews.addListCategory(newCategory);
        boolean f;
        if(k == serviceOfNews.getServiceCategory().size()-1) f = true;
        else f = false;
        return new Answer_Rest("boolean",Boolean.toString(f));
    }
}
