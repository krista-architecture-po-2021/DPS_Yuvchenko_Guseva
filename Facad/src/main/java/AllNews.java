import ModelDataClass.DataNews;
import ModelDataClass.NoNewsException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllNews implements IAllNews {
    //public java.util.List<News> newsList;

//    public AllNews() {
//        newsList = new ArrayList<>();
//
//    }

    public List<News> getList() {
        ArrayList<News> list = new ArrayList<>();
        ArrayList<DataNews> dataNewsArrayList = ModelNews.getListNews();
        if (dataNewsArrayList!=null){
        for (DataNews dataNews : dataNewsArrayList) {
            list.add(new News(dataNews.name, dataNews.content, dataNews.image, dataNews.category));
        }
        return list;}
        return null;
    }

    public News getNews(String name) throws NoNewsException {
        DataNews dataNews = ModelNews.getDataNews(name);
        return new News(dataNews.name, dataNews.content, dataNews.image, dataNews.category);
    }

    public void add(News news) {
        DataNews dataNews = new DataNews(news.getName(),news.getImage(),news.getContent(),news.getCategory());
        ModelNews.saveNewsToFiles(dataNews);
    }

    public void delete(String name) {
        ModelNews.deleteDataNews(name);
    }

    public void delete(News news) {
        ModelNews.deleteDataNews(news.getName());
    }
}


class News {
    String name;
    String content;
    Image image;
    String category;

    public News(String name, String content, Image image, String category) {
        this.name = name;
        this.content = content;
        this.image = image;
//        try {
//            image = ImageIO.read(new File(filenameImage));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        this.category = category;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImage(String filenameImage) {
        try {
            image = ImageIO.read(new File(filenameImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
