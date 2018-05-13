package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;

/**
 * gkislin
 * 21.07.2016
 */
public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");

/*        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        new PrettyDeeplyPrintDirectory(dir);  // для отступов мне понадобился нестатический метод (из-за переменной prefix )
    }
}


class PrettyDeeplyPrintDirectory {
    PrettyDeeplyPrintDirectory(File file1) {
        try {
            pritDir(file1," ");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void pritDir(File dir, String prefix) throws Exception {   //static
        File[] files = dir.listFiles();
        if (files != null) {
            for (File name : files) {
                System.out.println(prefix.concat(name.toString()));
                if (name.isDirectory()) {
                    pritDir(name, prefix.concat("  "));
                }
            }
        }
    }
}