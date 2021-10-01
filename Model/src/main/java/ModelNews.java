import ModelDataClass.DataNews;
import ModelDataClass.NoNewsException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class ModelNews {

    private static HashSet<String> hashset = getSetOfString();
    public static HashSet<String> getSetOfString () {
        HashSet <String> hashSet = new HashSet<>();
        try (Scanner scan = new Scanner(new FileReader("Resources/ListFileName.txt"))) {
            for (int i = 0; scan.hasNext(); i++)
                hashSet.add(scan.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashSet;
    }

    public static DataNews getDataNews(String filename) throws NoNewsException {
        try (Scanner scan = new Scanner(new FileReader("Resources/" + filename + "" + ".txt"))) {
            String category = scan.nextLine();
            String content = scan.nextLine();
            Image image = ImageIO.read(new File("Resources/" + filename + "" + ".jpg"));
            return new DataNews(filename, image, content, category);
        } catch (IOException e) {
            //e.printStackTrace();
            throw new NoNewsException();
        }
    }

    public static ArrayList<DataNews> getListNews() {
        ArrayList<DataNews> listdatanews = new ArrayList<>();
        HashSet<String> nameFileNews = hashset;//set с именами файлов
        for (String nameFile : nameFileNews) {
            try {
                listdatanews.add(getDataNews(nameFile));
            } catch (NoNewsException e) {
                e.printStackTrace();
            }
        }
        if (listdatanews.size() > 0) return listdatanews;
        return null;
    }

    /**
     * сохраняет одну новость
     * @param dataNews
     */
    public static void saveNewsToFiles(DataNews dataNews) {
        PrintWriter pw = null;
        try {
            System.out.println("1");
            pw = new PrintWriter("C:\\Users\\term4\\Desktop\\LabDPS3\\Facad\\Resources\\" + dataNews.name + ".txt");
            pw.write(dataNews.category + System.lineSeparator());
            pw.write(dataNews.content);
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        File outputfile = new File("Resources/" + dataNews.name + "" + ".jpg");
        BufferedImage image = new BufferedImage(dataNews.image.getWidth(null), dataNews.image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        try {
            ImageIO.write(image, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!hashset.contains(dataNews.name)) {
            hashset.add(dataNews.name);
            try (FileWriter file = new FileWriter("Resources\\ListFileName.txt", true)) {
                file.append(dataNews.name + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * удаляет файлы для формирования новости
     * @param name
     */
    public static void deleteDataNews(String name) {
        File filed = new File("Resources/" + name + ".jpg");
        filed.delete();
        filed = new File("Resources/" + name + ".txt");
        filed.delete();
        hashset.remove(name);

        try (FileWriter file = new FileWriter("Resources\\ListFileName.txt")) {
            for(String name1: hashset){
                file.write(name+System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateNameOfNews(String name, String newName){
        try {
            DataNews dataNews = getDataNews(name);
            File file = new File(name+".txt");
            file.delete();
            file = new File(name+".jpg");
            file.delete();

            dataNews.name=newName;
            saveNewsToFiles(dataNews);

        } catch (NoNewsException e) {
            e.printStackTrace();
        }
    }

    public static void updateContentOfNews(String name, String newContent){
        try {
            DataNews dataNews = getDataNews(name);
            File file = new File(name+".txt");
            file.delete();
            file = new File(name+".jpg");
            file.delete();

            dataNews.content=newContent;
            saveNewsToFiles(dataNews);

        } catch (NoNewsException e) {
            e.printStackTrace();
        }
    }




}