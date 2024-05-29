import java.util.ArrayList;

public class Book {
    private Author au;
    private ArrayList<Publisher> pub;
    private int max, count = -1;

    public Book() {
        au = new Author();
        max = 0;
        pub = new ArrayList<Publisher>();
    }

    public Book(Author au, int n) {
        this.au = au;
        max = n;
        pub = new ArrayList<Publisher>();
    }

    public void setAuthor(Author au) {
        this.au = au;
    }

    public void setPublisher(Publisher pub) {
        this.pub.add(pub);
    }

    public void setPublisher(Publisher pub, int n) {
        this.pub.set(n, pub);
    }

    public String getAuthor() {
        return au.toString();
    }

    public String getPublisher(int n) {
        return pub.get(n).toString();
    }
}
