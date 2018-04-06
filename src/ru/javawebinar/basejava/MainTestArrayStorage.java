package ru.javawebinar.basejava;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.ArrayStorage;

/**
 * Test ru.javawebinar.basejava.storage.ArrayStorage
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

   //     System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());


        writeResume(r3);
        //printResume(r3);

    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }

    static  void writeResume(Resume resume){
/*

        resume.setElementsResume(SectionType.ACHIEVEMENT, new TextSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven"));
       // resume.setElementsResume(SectionType.EDUCATION,new ExperienceSection());
      //  resume.setElementsResume(SectionType.EXPERIENCE,new ExperienceSection());
        resume.setElementsResume(SectionType.OBJECTIVE,new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.setElementsResume(SectionType.PERSONAL,new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры. "));
        resume.setElementsResume(SectionType.QUALIFICATIONS,new TextSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 "));
    }

    static void printResume(Resume resume) {
        for (SectionType type : SectionType.values()
                ) {
            AllSection textSection =resume.getElementsResume(type);
            System.out.println(textSection);
        }

*/
    }

}