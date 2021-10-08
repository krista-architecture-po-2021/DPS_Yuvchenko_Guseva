package ModelPackage;

import ModelDataAccesObject.DataCategoryDAO;
import ModelDataClass.DataCategory;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModelCategoryFileDAO implements IModelCategoryDAO{

    /**
     * Возвращает лист категорий
     * @return
     */
    public ArrayList<DataCategory> getListCategory() {
        ArrayList<DataCategory> datacat = new ArrayList<>();
        try (Scanner scan = new Scanner(new FileReader("Resources/ListCategory.txt"))) {
            for (int i = 0; scan.hasNext(); i++) {
                String str = scan.nextLine();
                datacat.add(new DataCategory(str));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datacat;
    }

}
