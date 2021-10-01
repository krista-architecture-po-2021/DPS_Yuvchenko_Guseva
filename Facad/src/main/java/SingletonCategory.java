import java.util.ArrayList;
import java.util.List;

public class SingletonCategory {
    private static List<Category> singleton = null;
    private SingletonCategory() { }
    public static synchronized List<Category> getInstance() {
        if (singleton == null) {
            singleton = new ArrayList<Category>();
        }
        return singleton;
    }
}

class SingletonCategory1 {
    private List<Category> listallcategory;
    private static SingletonCategory1 singleton = null;
    private SingletonCategory1() {
        listallcategory = new ArrayList<>();
    }
    public static synchronized List<Category> getInstance() {
        if (singleton == null) {
            singleton = new SingletonCategory1();
        }
        return singleton.listallcategory;
    }
}