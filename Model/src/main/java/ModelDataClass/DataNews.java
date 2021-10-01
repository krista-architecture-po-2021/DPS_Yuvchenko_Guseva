package ModelDataClass;

import java.awt.*;

public class DataNews {

    public String name;
    public Image image;
    public String content;
    public String category;

    public DataNews(String name, Image image, String content, String category){
        this.category = category;
        this.content = content;
        this.image = image;
        this.name = name;
    }
}
