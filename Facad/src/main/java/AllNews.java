import ModelDataClass.DataNews;
import ModelDataClass.NoDataFileException;
import ModelPackage.Factory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AllNews implements IAllNews {

    //private Factory model = Factory.getModel("ModelFile");
    private Factory model = Factory.getModel("ModelFile");

    public AllNews(String model) {
        this.model = Factory.getModel(model);
    }

    public List<News> getList() {
        ArrayList<News> list = new ArrayList<>();
        ArrayList<DataNews> dataNewsArrayList = model.getModelNewsDAO().getListNews();
        if (dataNewsArrayList != null) {
            for (DataNews dataNews : dataNewsArrayList) {
                list.add(new News(dataNews.name, dataNews.content, dataNews.image, dataNews.category, dataNews.author, dataNews.datetime));
            }
            return list;
        }
        return null;
    }

    public News getNews(String name) {
        DataNews dataNews = null;
        try {
            dataNews = model.getModelNewsDAO().getDataNews(name);
            if(dataNews!=null) return new News(dataNews.name, dataNews.content, dataNews.image,
                                    dataNews.category, dataNews.author, dataNews.datetime);
        } catch (NoDataFileException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(News news) {
        DataNews dataNews = new DataNews(news.getName(), news.getImage(), news.getContent(), news.getCategory(), news.getAuthor(), news.getDatatime());
        model.getModelNewsDAO().saveNews(dataNews);
    }

    public void delete(String name) {
        model.getModelNewsDAO().deleteDataNews(name);
    }

    @Override
    public void updateNews(String oldName, News news) {
        DataNews dataNews = new DataNews(news.getName(), news.getImage(), news.getContent(), news.getCategory(), news.getAuthor(), news.getDatatime());
        model.getModelNewsDAO().updateNews(oldName, dataNews);
    }
}


class News implements Serializable {
    String name;
    String content;
    Image image;
    String category;
    String author;
    String datatime;

    public News(String name, String content, Image image, String category, String author, String data) {
        this.name = name;
        this.content = content;
        this.image = image;
        this.category = category;
        this.author = author;
        this.datatime = data;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public String getAuthor() {
        return author;
    }

    public String getDatatime() {
        return datatime;
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