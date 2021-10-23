package Providers;

import ModelDataClass.DataCategory;
import ModelDataClass.DataNews;
import ModelDataClass.NoDataFileException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

public class ProviderFiles implements IProvider {

    public <T> ArrayList<T> getList(String type) throws NoDataFileException {
        int k = ("NEWS".equals(type.toUpperCase(Locale.ROOT))) ? 5 : 0;
        String filename = (k == 5) ? "Resources/FileNews.txt" : "Resources/ListCategory.txt";
        ArrayList<T> arrayList = new ArrayList<>();
        ArrayList<String> masdataobj = new ArrayList<>();
        try (Scanner scan = new Scanner(new FileReader(filename))) {
            for (int i = 0; scan.hasNext(); i++) {
                String str = scan.nextLine();
                masdataobj = new ArrayList<>();
                masdataobj.add(str);
                for (int ii = 0; ii < k-1; ii++) {
                    str = scan.nextLine();
                    masdataobj.add(str);
                }
                if(k==5) arrayList.add((T) new DataNews(masdataobj));
                else arrayList.add((T) new DataCategory(masdataobj));
            }
        } catch (IOException e) {
            //e.printStackTrace();
            throw new NoDataFileException();
        }
        return arrayList;
    }


    public <T> T getDO(String entityName) throws NoDataFileException {
        String[] masdata = entityName.split(" ");
        int k = ("NEWS".equals(masdata[0].toUpperCase(Locale.ROOT))) ? 5 : 0;
        String filename = (k == 5) ? "Resources/FileNews.txt" : "Resources/ListCategory.txt";

        ArrayList<String> masdataobj = new ArrayList<>();
        try (Scanner scan = new Scanner(new FileReader(filename))) {
            for (int i = 0; scan.hasNext(); i++) {
                String str = scan.nextLine();
                if (str.equals(masdata[1])) {
                    masdataobj.add(str);
                    for (int ii = 0; ii < k-1; ii++) {
                        str = scan.nextLine();
                        masdataobj.add(str);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new NoDataFileException();
        }
        if(masdataobj.size()>0){
            if(k==5) return (T) new DataNews(masdataobj);
            else return (T) new DataCategory(masdataobj);
        }
        return null;
    }

    public <T> void save(String entityName, T dO){
        String[] masdata = entityName.split(" ");
        if(!check(masdata[0],masdata[1])) {
            int k = ("NEWS".equals(masdata[0].toUpperCase(Locale.ROOT))) ? 5 : 1;
            String filename = (k == 5) ? "Resources/FileNews.txt" : "Resources/ListCategory.txt";

            //дописываем в файл
            PrintWriter pw;
            try {
                pw = new PrintWriter(new FileWriter(filename, true));
                if(k==5) {
                    DataNews dataNews = (DataNews) dO;
                    pw.write(dataNews.name);
                    pw.write(System.lineSeparator());
                    pw.write(dataNews.content);
                    pw.write(System.lineSeparator());
                    pw.write(dataNews.category);
                    pw.write(System.lineSeparator());
                    pw.write(dataNews.author);
                    pw.write(System.lineSeparator());
                    pw.write(dataNews.datetime);
                    pw.write(System.lineSeparator());
                } else {
                    DataCategory dataCategory = (DataCategory) dO;
                    pw.write(dataCategory.category);
                    pw.write(System.lineSeparator());
                }
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean check(String type, String name){
        int k = ("NEWS".equals(type.toUpperCase(Locale.ROOT))) ? 5:1;
        String filename = (k == 5) ? "Resources/FileNews.txt" : "Resources/ListCategory.txt";

        try (Scanner scan = new Scanner(new FileReader(filename))) {
            for (int i = 0; scan.hasNext(); i++) {
                String str = scan.nextLine();
                if(str.equals(name))return true;
                }
            } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return false;
    }

    /**
     * Метод либо обновляет нужную запись, либо удаляет её
     * Для удаления dO должен быть null
     * @param entityName
     * @param dO
     * @param <T>
     */
    public <T> void update_delite(String entityName, T dO) {
        String[] masdata = entityName.split(" ");
        int k = ("NEWS".equals(masdata[0].toUpperCase(Locale.ROOT))) ? 5:1;
        String filename = (k == 5) ? "Resources/FileNews.txt" : "Resources/ListCategory.txt";
        String filename2 = (k == 5) ? "Resources/FileNewsCopy.txt" : "Resources/ListCategoryCopy.txt";

        File file1 = new File(filename);
        File file2 = new File(filename2);
        file2.delete();
        try {
            //file2.createNewFile();
            Files.copy(file1.toPath(),file2.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        file1.delete();
        //создаём файл
        PrintWriter pw;
        try {
            pw = new PrintWriter(file1);

            try (Scanner scan = new Scanner(new FileReader(file2))) {
                for (int i = 0; scan.hasNext(); i++) {
                    String str = scan.nextLine();
                    if (str.equals(masdata[1])) {
                        if(dO!=null){
                            if(k==5) {
                                DataNews dataNews = (DataNews) dO;
                                pw.write(dataNews.name);
                                pw.write(System.lineSeparator());
                                pw.write(dataNews.content);
                                pw.write(System.lineSeparator());
                                pw.write(dataNews.category);
                                pw.write(System.lineSeparator());
                                pw.write(dataNews.author);
                                pw.write(System.lineSeparator());
                                pw.write(dataNews.datetime);
                                pw.write(System.lineSeparator());
                            } else {
                                DataCategory dataCategory = (DataCategory) dO;
                                pw.write(dataCategory.category);
                            }
                            for(int i1=0;i1<k-1;i1++)scan.nextLine();
                        }else for(int i1=0;i1<k-1;i1++)scan.nextLine();
                    } else {
                        pw.write(str);
                        pw.write(System.lineSeparator());
                        for(int i1=0;i1<k-1;i1++){
                            pw.write(scan.nextLine());
                            pw.write(System.lineSeparator());
                            }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }


}
