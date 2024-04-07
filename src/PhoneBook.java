/*Задание:

Реализуйте структуру телефонной книги с помощью HashMap.
Программа также должна учитывать, что во входной структуре будут
повторяющиеся имена с разными телефонами, их необходимо считать, как
одного человека с разными телефонами. Вывод должен быть отсортирован
по убыванию числа телефонов.*/


import java.util.*;

public class PhoneBook {
    public static void main(String[] args) {
        Map<String, List<String>> phoneBook = new HashMap<>();

        addContact(phoneBook, "Васечкин", "458-84-35");
        addContact(phoneBook, "Рубцова", "325-89-54");
        addContact(phoneBook, "Рубцова", "325-89-51");
        addContact(phoneBook, "Васечкин", "290-30-41");
        addContact(phoneBook, "Хохлова", "854-25-88");
        addContact(phoneBook, "Васечкин", "325-12-90");
        addContact(phoneBook, "Спевакова", "545-15-25");
        addContact(phoneBook, "Хохлова", "218-30-48");

        printPhoneBook(phoneBook);
    }

    private static void addContact(Map<String, List<String>> phoneBook, String name, String phoneNumber) {
        // Если имя уже существует в книге, добавляю телефон к существующему списку
        phoneBook.computeIfAbsent(name, k -> new ArrayList<>()).add(phoneNumber);
    }

    private static void printPhoneBook(Map<String, List<String>> phoneBook) {
        List<Map.Entry<String, List<String>>> entries = new ArrayList<>(phoneBook.entrySet());
        entries.sort((entry1, entry2) -> entry2.getValue().size() - entry1.getValue().size()); // сортировка по убыванию числа телефонов

        int maxNameLength = getMaxNameLength(phoneBook);

        for (Map.Entry<String, List<String>> entry : entries) {
            String name = entry.getKey();
            List<String> phoneNumbers = entry.getValue();

            String formattedPhoneNumbers = String.join(", ", phoneNumbers);
            System.out.printf("%-" + (maxNameLength + 1) + "s: %s%n", name, formattedPhoneNumbers);
        }
    }

    private static int getMaxNameLength(Map<String, List<String>> phoneBook) {
        int maxNameLength = 0;
        for (String name : phoneBook.keySet()) {
            maxNameLength = Math.max(maxNameLength, name.length());
        }
        return maxNameLength;
    }
}


