package sample;

public class Items {
    int number;
    String title;
    String Author;
    String isbn;
    int quantity;
    String location;
    String type;

    Items(Integer n, String t, String i, String A, String l, int q, String ty){
        number=n;
        title = t;
        isbn = i;
        Author = A;
        location = l;
        quantity = q;
        type = ty;


    }

    public String toString(){
        return (title + isbn + Author + quantity + location + type);
    }
}
