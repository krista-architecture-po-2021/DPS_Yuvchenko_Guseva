import com.google.gson.Gson;

import java.util.List;

public class API_3_Reader {
    static final String model = "ModelFile";
    static final boolean[] flags = {false, true, true};

    public static synchronized Answer_Rest getListNews() {
        Gson g = new Gson();
        IOtoFacad io = new IOtoFacad(model,flags);
        String str = g.toJson(new FacadeService(io).getListNews());
        return new Answer_Rest("List<News>",str);
    }

    public static synchronized Answer_Rest getNews(String nameNews){
        Gson g = new Gson();
        IOtoFacad io = new IOtoFacad(model,flags);
        String str = g.toJson(new FacadeService(io).getNews(nameNews));
        return new Answer_Rest("List<News>",str);

    }

}
