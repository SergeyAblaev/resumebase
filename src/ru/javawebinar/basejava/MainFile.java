package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * gkislin
 * 21.07.2016
 */
public class MainFile {

    public static void main(String[] args) {
        String filePath = ".\\.gitignore";
        //StringBuffer

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
            System.out.println(file.getPath());
            System.out.println(file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
            //    (char ) fis.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
  // Я не знал как вызватьиз статик метода, нестатический. Поэтому вынес рекурсию в отдельный класс.
new RecursiveMethods();
    }
}


    class RecursiveMethods {
       // public static StringBuilder sb = new StringBuilder();

        RecursiveMethods() {
     System.out.println("now print recursion");

        File file1 = new File("./");

            try {
                pritDir(file1,"_");
            } catch (Exception e1) {
                e1.printStackTrace();
            }

    }

        public void pritDir(File dir, String prefix) throws Exception {   //static

            File[] files = dir.listFiles();

            //  String[] list = dir.list();
            if (files != null) {

                for (File name : files) {
                    if (name.isDirectory()) {
                       // sb.append("--");
                        System.out.println(prefix.concat(name.toString()));
                        pritDir(name, prefix.concat("_")); //sb.toString()
                    } else {
                        System.out.println(prefix.concat(name.toString()));
                    }
                }

            }


        }
    }