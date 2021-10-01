import org.junit.jupiter.api.*;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceOfNewsTest {
    public FacadeService serviceOfNews;

    @BeforeEach
    public void setUp() {
        serviceOfNews = new FacadeService();
        serviceOfNews.addListCategory("Ерунда");
        News news = null;
//        System.out.println("34");
//        try {
//            FileOutputStream fos = new FileOutputStream(new File("ResourcesTest\\потоп.txt"),true);
////            Files.copy(Paths.get("Resources"),fos);
//            fos.write(Integer.parseInt("32"));
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            news = new News("Потоп","Ливневые дожди залили весь юг России", ImageIO.read(new File("ResourcesTest/потоп.jpg")), "Ерунда");
            serviceOfNews.addNews(news);
            serviceOfNews.addListCategory("Важное");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getListNews() {
        assertNotNull(serviceOfNews.getServiceNews());
    }

    @Test
    public void getNews() {
        News news = serviceOfNews.getNews("Потоп");
        assertNotNull(news);
    }

    @Test
    public void addNews() {
        News news = null;
        try {
            news = new News("Ту-160","Ту-160 пролетели вдоль южных границ России", ImageIO.read(new File("ResourcesTest/ту-160.jpg")), "Важное");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int k = serviceOfNews.getServiceNews().size();
        serviceOfNews.addNews(news);
        assertEquals(k,serviceOfNews.getServiceNews().size()-1);
    }

    @Test
    public void updateNewsName() {
        News news = serviceOfNews.getNews("Потоп");
        String name = news.getName();
        serviceOfNews.updateNewsName("Потоп","Ужасный потоп");
        News news1 = serviceOfNews.getNews("Ужасный потоп");
        assertNotEquals(name,news1.getName());
    }

    @Test
    public void updateNewsContent() {
        News news = serviceOfNews.getNews("Потоп");
        String content = news.getContent();
        serviceOfNews.updateNewsContent("Потоп","Ливневые дожди пришли в центральную Россию");
        News news1 = serviceOfNews.getNews("Потоп");
        assertNotEquals(content,news1.getContent());
    }


    @Test
    public void deleteNews() {
        serviceOfNews.deleteNews("Потоп");
        assertEquals(serviceOfNews.getServiceNews().size(),2);
    }

    @Test
    public void testDeleteNews() {
        News news = serviceOfNews.getNews("Потоп");
        serviceOfNews.deleteNews(news);
        assertNull(serviceOfNews.getServiceNews());

    }

    @Test
    public void getListCategory() {
        assertNotNull(serviceOfNews.getServiceCategory());
    }

    @Test
    public void addListCategory() {
        int k = serviceOfNews.getServiceCategory().size();
        serviceOfNews.addListCategory("Предмет");
        assertEquals(k,serviceOfNews.getServiceCategory().size()-1);
    }

    @Test
    public void updateCategory() {
        serviceOfNews.updateCategory("Ерунда","Природная катастрофа (не у нас)");
        //assertEquals();
    
    }

    @Test
    public void deleteCategory() {
        News news = serviceOfNews.getNews("Потоп");
        serviceOfNews.deleteCategory("Ерунда");
        assertEquals("\n",news.getCategory());
    }
}