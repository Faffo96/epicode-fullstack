import Ecommerce.Article;
import Ecommerce.Cart;
import Ecommerce.User;
import PhoneSim.Call;
import PhoneSim.Sim;
import Rectangle.Rectangle;

public class Main {
    public static void main(String[] args) {
        /*RECTANGLE*/
        System.out.println("RECTANGLE EXERCISE");

        Rectangle rectangle1 = new Rectangle(5, 4);
        Rectangle rectangle2 = new Rectangle(3, 7);

        rectangle1.getRectangle(rectangle1);

        rectangle1.get2Rectangles(rectangle1, rectangle2);

        /*SIM*/
        System.out.println("SIM EXERCISE:");

        Sim sim1 = new Sim(338129384);

        Call[] calls = new Call[5];
        Call call1 = new Call(421423524, 100);
        Call call2 = new Call(521423524, 200);
        Call call3 = new Call(621423524, 300);
        Call call4 = new Call(721423524, 400);
        Call call5 = new Call(821423524, 500);

        calls[0] = call1;
        calls[1] = call2;
        calls[2] = call3;
        calls[3] = call4;
        calls[4] = call5;

        System.out.println("$10 credit added to SIM1 card");
        sim1.setCredit(15);
        System.out.println("5 calls are made");
        sim1.setCalls(calls);


        sim1.getSimInfos();

        /*ECOMMERCE EXERCISE*/
        System.out.println("ECOMMERCE EXERCISE:");

        User user1 = new User("Fabio", "Scaramozzino", "fabio@scaramozzino.com");

        Article[] articles = new Article[5];

        Article article1 = new Article("Water bottle", 1, 1);
        Article article2 = new Article("CocaCola bottle", 2, 1);
        Article article3 = new Article("Sandwich", 1.5, 2);
        Article article4 = new Article("Chocolate", 2.5, 1);
        Article article5 = new Article("Paper Towel", 2, 1);

        articles[0] = article1;
        articles[1] = article2;
        articles[2] = article3;
        articles[3] = article4;
        articles[4] = article5;

        double getTotalCartPrice = Cart.getTotalCartPrice(articles);
        Cart cart1 = new Cart(articles, getTotalCartPrice, user1);
        System.out.println("The user with email: " + cart1.getUser().getEmail() + "  has added to the cart: ");
        cart1.getArticles();
        System.out.println("The cart's total is: " + getTotalCartPrice + "$");
    }
}
