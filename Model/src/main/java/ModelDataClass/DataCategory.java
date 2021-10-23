package ModelDataClass;


import java.util.ArrayList;

public class DataCategory {

    public String category;

    public DataCategory(String nameCategory){
        this.category = nameCategory;
    }
    public DataCategory(ArrayList<String> masdatacat){
        this.category=masdatacat.get(0);
    }

}
