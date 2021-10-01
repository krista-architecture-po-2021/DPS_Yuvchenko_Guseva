import ModelDataClass.NoNewsException;

import java.util.List;


public class FacadeService {
    private IAllNews serviceNews = new AllNews();
    private IAllCategory serviceCategory = new AllCategory();


    public List<News> getServiceNews() {
        return serviceNews.getList();
    }

    public News getNews(String name) {
        try {
            return serviceNews.getNews(name);
        } catch (NoNewsException e) {
            return null;
        }
    }

    public void addNews(News news) {
        serviceNews.add(news);
    }

    public void updateNewsName(String name, String newname) {
        ModelNews.updateNameOfNews(name,newname);
    }

    public void updateNewsContent(String name, String newcontent) {
        ModelNews.updateContentOfNews(name,newcontent);
    }

    public void deleteNews(String name) {
        serviceNews.delete(name);
    }

    public void deleteNews(News news) {
        serviceNews.delete(news);
    }


    public List<String> getServiceCategory() {
        return serviceCategory.getList();
    }

    public void addListCategory(String name) {
        if (!serviceCategory.getList().contains(name)) {
            serviceCategory.add(name);
        }
    }

    public void updateCategory(String name, String newName) {
        serviceCategory.getList().remove(name);
        serviceCategory.getList().add(name);
    }

    public void deleteCategory(String name){
        for (News news : serviceNews.getList()) {
            if (news.getCategory().equals(name)) {
                news.setCategory("\n");
            }
        }
        serviceCategory.getList().remove(name);
    }
}
