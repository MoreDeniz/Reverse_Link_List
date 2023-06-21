import java.util.Iterator;

public class Reverse {
    public static void main(String[] args) {
        SingleLinkList<Contact> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Contact(1, "Боброва Наталья", "1234567"));
        contactList.addToEnd(new Contact(2, "Лебедева Яна", "2345678"));
        contactList.addToEnd(new Contact(3, "Степанов Сергей", "3456789"));
        contactList.addToEnd(new Contact(4, "Кауфман Юрий", "4567890"));
        contactList.addToEnd(new Contact(5, "Зеваков Сергей", "5678901"));

        System.out.println("Исходный список:");

        for (Contact contact: contactList) {
            System.out.println(contact);
        }

        contactList.reverse();

        System.out.println("Развернутый список:");

        for (Contact contact: contactList) {
            System.out.println(contact);
        }
    }

    // класс данных
    static class Contact {
        int id;
        String name;
        String phone;

        // конструктор
        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact(" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ')';
        }
    }

    public static class SingleLinkList<T> implements Iterable<T> {

        ListItem<T> head;
        ListItem<T> tail;

        // последовательный доступ к элементам списка
        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        // проверка на пустой список
        public boolean isEmpty() {
            return head == null;
        }

        // вставка в конец списка
        public void addToEnd(T item) {
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;
            if (isEmpty()) {
                head = newItem;
                tail = newItem;
            } else {
                tail.next = newItem;
                tail = newItem;
            }
        }

        // функция разворота
        public void reverse() {
            if (!isEmpty() && head.next != null) {
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}
