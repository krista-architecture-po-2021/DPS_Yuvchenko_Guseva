package Providers;

import ModelDataClass.DataCategory;
import ModelDataClass.DataNews;
import ModelDataClass.NoDataFileException;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

public interface IProvider {

//    public void saveNews(DataNews dataNews);
//    public HashSet<String> getSetOfString ();
//    public String[] getTextOfNews(String nameNews) throws NoDataFileException;
//    public Image getImageOfNews(String nameNews) throws NoDataFileException;
//    public void deleteDataNews(String name);
//    public void updateContent(String name, String newContent);
//    public void updateNameOfNews(String name, String newName);
//
//    public ArrayList<String> getListCategory();
//    public void addCategory(String nameCategory);

    public <T> ArrayList<T> getList(String type) throws NoDataFileException;
    public <T> T getDO(String entityName) throws NoDataFileException;
    public <T> void save(String entityName, T dO);
    public boolean check(String type, String name);
    public <T> void update_delite(String entityName, T dO);

}
