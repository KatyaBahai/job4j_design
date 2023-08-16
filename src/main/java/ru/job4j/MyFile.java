package ru.job4j;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyFile {
    private String fileName;
    private File directory;

    public MyFile(String fileName, File directory) {
        this.fileName = fileName;
        this.directory = directory;
    }

    public String getFileName() {
        return fileName;
    }

    public File getDirectory() {
        return directory;
    }

    public List<File> fileFinder(MyFile myFile) {
        List<File> finalList = new ArrayList<>();
        if (!myFile.getDirectory().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", myFile.getDirectory().getAbsoluteFile()));
        }
        if (!myFile.getDirectory().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", myFile.getDirectory().getAbsoluteFile()));
        }

        File[] filesArray = myFile.getDirectory().listFiles();
        if (filesArray == null) {
            throw new IllegalArgumentException(String.format("The directory %s is empty", myFile.getDirectory().getAbsoluteFile()));
        }
        for (File file : filesArray) {
            if (myFile.getFileName().equals(file.getName())) {
                finalList.add(file);
            }
        }
        return finalList;
    }

    public void filePrinter(List<File> fileList) {
        for (File file : fileList) {
            Calendar date = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.printf("%s - %s", file, formatter.format(date.getTime()));
        }
    }

    public static void main(String[] args) {
        MyFile myFile = new MyFile("even.txt", new File("C:\\projects\\job4j_design\\data"));
        myFile.filePrinter(myFile.fileFinder(myFile));
    }


}
