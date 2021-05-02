package Homework.Exercise5_HashCode;
import java.util.*;

/* Указываем тип, с которым может сравниваться класс,
 * реализующий Comparable.
 * Получается, что при сортировке объекты Book могут
 * сравниваться с другими объектами Book. */
class Book implements Comparable<Book> {
    private String title;
    private String author;
    private int year;
    private int pages;

    Book(String title, String author, int year, int pages) {
        this.year = year;
        this.pages = pages;
        this.author = author;
        this.title = title;
    }

    /* Метод sort передает объект Book в compareTo(), чтобы
     * увидеть, как тот соотносится с экземпляром Book,
     * из которого вызван метод. */
    @Override
    public int compareTo(Book otherBook) {
        /* У string есть метод compareTo, поэтому
         * перекладываем всю работу на него. */
        return this.title.compareTo(otherBook.getTitle());
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return title;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode() + author.hashCode();
        result = 31 * result + year;
        result = 31 * result + pages;
        return result;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Book book = (Book) obj;

        return (this.author.equals(book.author) &&
                this.year == book.year &&
                this.pages == book.pages);
    }
}

/**
 * Пример сортировки коллекций с использованием метода Collections.sort()
 * Классы объектов, помещаемых в коллекцию, предназначенной для сортировки, должны удовлетворять одному условию:
 * реализовывать интерфейс Comparable.
 * Класс String реализовывает этот интерфейс, а класс Book мы написали сами, поэтому необходимо позаботиться
 * о реализации интерфейса самостоятельно. Без этого метод sort не будет знать, по каким критериям производить
 * сортировку.
 */
public class Exercise5_HashCodeRewrite {
    public static void main(String[] args) {
        String b1 = "Сказки";
        String b2 = "Физика";
        String b3 = "Программирование на Java";

        var shelf = new ArrayList<String>();
        shelf.add(b1);
        shelf.add(b2);
        shelf.add(b3);

        System.out.println(shelf);
        Collections.sort(shelf);
        System.out.println(shelf);

        System.out.println();

        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book("The great Gatsby", "Fitzgerald", 1999, 2000));
        books.add(new Book("Atlas shrugged", "Rand", 1987, 120));
        books.add(new Book("Animal farm", "Orwell", 2001, 629));
        books.add(new Book("1984", "Orwell", 1979, 1340));

        System.out.println("Before sort ================================");
        System.out.println(books);

        Collections.sort(books);

        System.out.println("\nAfter sort ================================");
        System.out.println(books);

        Book book1 = new Book("Fitzgerald", "The great Gatsby", 1999, 2000);
        Book book2 = new Book("Fitzgerald", "The great Gatsby", 1999, 2000);
        Book book3 = new Book("Fitzgerald", "The great Gatsby", 19992, 20002);

        /* System.out.println(book1.equals(book2));
        System.out.println(book2.equals(book1));
        System.out.println(book3.equals(book1)); */

        //hashCode() overrided
        System.out.println(book1.hashCode());
        System.out.println(book2.hashCode());
        System.out.println(book3.hashCode());

        if (book1.hashCode() == book2.hashCode()) {
            System.out.println("Хэш-коды равны");
        }

        if (book1.hashCode() == book3.hashCode()) {
            System.out.println("Хэш-коды равны");
        } else System.out.println("Не равны");
    }
}
