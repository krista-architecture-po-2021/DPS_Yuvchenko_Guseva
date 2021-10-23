package ModelDataClass;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataNews {

    public String name;
    public Image image;
    public String content;
    public String category;
    public String author;
    public String datetime;

    public DataNews(String name, Image image, String content, String category, String author, String datetime){
        this.category = category;
        this.content = content;
        this.image = image;
        this.name = name;
        this.author = author;
        this.datetime = datetime;
    }

    public DataNews(ArrayList<String> masdataobj){
        try {
            this.image = ImageIO.read(new File("Resources/" + masdataobj.get(0) + "" + ".jpg"));
        } catch (IOException e) {
            //e.printStackTrace();
        }
        this.name=masdataobj.get(0);
        this.content=masdataobj.get(1);
        this.category=masdataobj.get(2);
        this.author=masdataobj.get(3);
        this.datetime=masdataobj.get(4);
    }

}
