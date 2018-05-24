package ru.javawebinar.basejava.storage.serializer;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            // TODO implements sections
            // first time: enumerator
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry: sections.entrySet()) {

                // тут еще один цикл по OrganizationSection.  только надо решить как выбирать последовательность секций = ведь восстановить надо в том же порядке.
                //перебором по енумератору наверное! - но там другие совсем имена. ? Разберись со структурой!
                System.out.print(entry.getKey().name()); // тут выводит SectionType по порядку енумератора!!!
                System.out.print("  /  ");
                System.out.println(entry.getValue());
                //    dos.writeUTF(entry.getKey().name());
            //    dos.writeUTF(entry.getValue());
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            // TODO implements sections
            return resume;
        }
    }
}