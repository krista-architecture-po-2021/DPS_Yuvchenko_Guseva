import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args){

        System.out.println(API_1_Admin.getListNews());

        News news = null;
        try {
            news = new News("Помните потоп?","Это было «совсем» относительно недавно, когда торнадо прошёлся по всем штатам", ImageIO.read(new File("ResourcesTest/потоп.jpg")), "Ерунда","Дик", "15,05,2021");
        } catch (IOException e) {
            e.printStackTrace();
        }
        API_2_Author.addNews(news);
        System.out.println(API_3_Reader.getNews("Потоп"));

    }
}
