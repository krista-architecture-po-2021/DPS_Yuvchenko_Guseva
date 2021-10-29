
import org.junit.jupiter.api.*;


import javax.imageio.ImageIO;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceOfNewsTest {
    public FacadeService serviceOfNewsFile;
    public FacadeService serviceOfNewsMas;

    @BeforeEach
    public void setUp() {
        int kod = 1;
        boolean[] flags = {true,false,false};
        IOtoFacad io = new IOtoFacad("ModelFile",flags);
        serviceOfNewsFile = new FacadeService(io);
        News news = null;
        try {
            news = new News("Потоп","Ливневые дожди залили весь юг России", ImageIO.read(new File("ResourcesTest/потоп.jpg")), "Ерунда","Журнал: За рулём", "15,05,2020");
            serviceOfNewsFile.addNews(news);
        } catch (IOException e) {
            e.printStackTrace();
        }
        serviceOfNewsFile.addListCategory("Ерунда");


        serviceOfNewsMas = new FacadeService(io);
        serviceOfNewsMas.addNews(news);


    }

    @Test
    public void getListNewsFile()
    {
       assertNotNull(serviceOfNewsFile.getListNews());
    }

    @Test
    public void getListNewsMas()
    {
        assertNotNull(serviceOfNewsMas.getListNews());
    }

    @Test
    public void getNewsFile() {
        News news = serviceOfNewsFile.getNews("Потоп");
        assertNotNull(news);
    }

    @Test
    public void getNewsMas() {
        News news = serviceOfNewsMas.getNews("Потоп");
        assertNotNull(news);
    }

    //Правильно работает?
    @Test
    public void addNewsFile() {
        News news = null;
        try {
            news = new News("Ту-160","Ту-160 пролетели вдоль южных границ России", ImageIO.read(new File("ResourcesTest/ту-160.jpg")), "Важное","Военное обозрение", "26,10,2019");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int k = serviceOfNewsFile.getListNews().size();
        serviceOfNewsFile.addNews(news);
        assertEquals(k, serviceOfNewsFile.getListNews().size());
    }

    //Правильно работает?
    @Test
    public void addNewsMas() {
        News news = null;
        try {
            news = new News("Ту-160","Ту-160 пролетели вдоль южных границ России", ImageIO.read(new File("ResourcesTest/ту-160.jpg")), "Важное","Военное обозрение", "26,10,2019");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int k = serviceOfNewsMas.getListNews().size();
        serviceOfNewsMas.addNews(news);
        assertEquals(k, serviceOfNewsMas.getListNews().size());
    }

    @Test
    public void updateNews() {
        News news = serviceOfNewsFile.getNews("Потоп");
        news.setName("Ужасный_потоп");
        serviceOfNewsFile.updateNews("Потоп",news);
        News news1 = serviceOfNewsFile.getNews("Ужасный_потоп");
        assertEquals("Ужасный_потоп",news1.getName());
    }


    @Test
    public void deleteNewsFile() {
        serviceOfNewsFile.deleteNews("Потоп");
        assertNull(serviceOfNewsFile.getNews("Потоп"));
    }
    @Test
    public void deleteNewsMas() {
        serviceOfNewsMas.deleteNews("Потоп");
        assertNull(serviceOfNewsMas.getNews("Потоп"));
    }

    @Test
    public void getListCategoryFile() {
        assertNotNull(serviceOfNewsFile.getServiceCategory());
    }
    @Test
    public void getListCategoryMas() {
        assertNotNull(serviceOfNewsMas.getServiceCategory());
    }


    @Test
    public void addListCategoryFile() {
        serviceOfNewsFile.deleteCategory("Предмет");
        int k = serviceOfNewsFile.getServiceCategory().size();
        serviceOfNewsFile.addListCategory("Предмет");
        assertEquals(k, serviceOfNewsFile.getServiceCategory().size()-1);
    }
    @Test
    public void addListCategoryMas() {
        serviceOfNewsMas.deleteCategory("Предмет");
        int k = serviceOfNewsMas.getServiceCategory().size();
        serviceOfNewsMas.addListCategory("Предмет");
        assertEquals(k, serviceOfNewsMas.getServiceCategory().size()-1);
    }

    @Test
    public void updateCategoryFile() {
        serviceOfNewsFile.addListCategory("Ерунда");
        serviceOfNewsFile.updateCategory("Ерунда","Природная катастрофа (не у нас)");
        assertTrue("Природная катастрофа (не у нас)".equals(serviceOfNewsFile.getCategory("Природная катастрофа (не у нас)").getNameCategory()));
    }
    @Test
    public void updateCategoryMas() {
        serviceOfNewsMas.addListCategory("Ерунда");
        serviceOfNewsMas.updateCategory("Ерунда","Природная катастрофа (не у нас)");
        assertTrue("Природная катастрофа (не у нас)".equals(serviceOfNewsMas.getCategory("Природная катастрофа (не у нас)").getNameCategory()));
    }

    @Test
    public void deleteCategoryFile() {
        serviceOfNewsFile.deleteCategory("Ерунда");
        assertNull(serviceOfNewsFile.getCategory("Ерунда"));
    }
    @Test
    public void deleteCategoryMas() {
        serviceOfNewsMas.deleteCategory("Ерунда");
        assertNull(serviceOfNewsMas.getCategory("Ерунда"));
    }

    @Test
    public void NoAuthorTest(){
        News news = null;
        try {
            news = new News("Ту-124","Ту-124 это пассажирский самолёт", ImageIO.read(new File("ResourcesTest/ту-124.jpg")), "Важное","МАКАР", "26,10,2019");
        } catch (IOException e) {
            e.printStackTrace();
        }
        serviceOfNewsFile.addNews(news);
        assertNull(serviceOfNewsFile.getNews("Ту-124"));
    }

    @Test
    public void NoAuthorListTest(){
        News news = null;
        try {
            news = new News("Ту-124","Ту-124 это пассажирский самолёт", ImageIO.read(new File("ResourcesTest/ту-124.jpg")), "Важное","МАКАР", "26,10,2019");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int k = serviceOfNewsFile.getListNews().size();
        serviceOfNewsFile.addNews(news);
        assertEquals(k,serviceOfNewsFile.getListNews().size());
    }


    @Test
    public void NoOldNewsTest(){
        boolean[] flags = {true,true,false};
        IOtoFacad io = new IOtoFacad("ModelFile",flags);
        serviceOfNewsFile = new FacadeService(io);
        News news = null;
        try {
            news = new News("Союз_МС-18","В этом году готовится к старту очередной союз <МС-18>.", ImageIO.read(new File("ResourcesTest/союз_мс-18.jpg")), "Важное","Жуков М.А.", "26,10,2019");
        } catch (IOException e) {
            e.printStackTrace();
        }
        serviceOfNewsFile.addNews(news);
        assertNull(serviceOfNewsFile.getNews("Союз_МС-18"));
    }

    @Test
    public void NoOldNewsListTest(){
        boolean[] flags = {true,true,false};
        IOtoFacad io = new IOtoFacad("ModelFile",flags);
        serviceOfNewsFile = new FacadeService(io);
        News news = null;
        try {
            news = new News("Союз_МС-18","В этом году готовится к старту очередной союз <МС-18>.", ImageIO.read(new File("ResourcesTest/союз_мс-18.jpg")), "Важное","Жуков М.А.", "26,10,2019");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int k = serviceOfNewsFile.getListNews().size();
        serviceOfNewsFile.addNews(news);
        assertEquals(k,serviceOfNewsFile.getListNews().size());
    }

    @Test
    public void NoWordsKovidTest(){
        boolean[] flags = {true,true,true};
        IOtoFacad io = new IOtoFacad("ModelFile",flags);
        serviceOfNewsFile = new FacadeService(io);
        News news = null;
        try {
            news = new News("Снова?","В очередной раз студентов хотят отправить на дистант из-за распространения коронавируса", ImageIO.read(new File("ResourcesTest/kovid19.jpg")), "Важное","Жуков М.А.", "22,10,2021");
        } catch (IOException e) {
            e.printStackTrace();
        }
        serviceOfNewsFile.addNews(news);
        assertNull(serviceOfNewsFile.getNews("Снова?"));
    }

    @Test
    public void NoWordsKovidListTest(){
        boolean[] flags = {true,true,true};
        IOtoFacad io = new IOtoFacad("ModelFile",flags);
        serviceOfNewsFile = new FacadeService(io);
        News news = null;
        try {
            news = new News("Снова?","В очередной раз студентов хотят отправить на дистант из-за распространения коронавируса", ImageIO.read(new File("ResourcesTest/kovid19.jpg")), "Важное","Жуков М.А.", "22,10,2021");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int k = serviceOfNewsFile.getListNews().size();
        serviceOfNewsFile.addNews(news);
        assertEquals(k,serviceOfNewsFile.getListNews().size());
    }

}