import java.util.List;

public interface IAllCategory {
    public List getList();
    public void addCategory(String name);
    public Category getCategory(String name);
    public void deleteCategory(String name);
    public void updateNameCategory(String name,String newName);
}
