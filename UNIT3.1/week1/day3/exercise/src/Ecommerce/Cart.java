package Ecommerce;

public class Cart {
    private User user;
    private Article[] articles;
    private double totalCartPrice;

    public Cart(Article[] articles, double totalCartPrice, User user) {
        this.articles = articles;
        this.totalCartPrice = totalCartPrice;
        this.user = user;
    }

    public void getArticles() {
        for (int i = 0; i < this.articles.length; i++) {
            System.out.println("Article: " + articles[i].getDescription() + ". Price: " + articles[i].getPrice() + "$. Quantity: " + articles[i].getQuantity());
        }
    }

    public void setArticles(Article[] articles) {
        this.articles = articles;
    }

    public static double getTotalCartPrice(Article articles[]) {
        double total = 0;

        for (int i = 0; i < articles.length; i++) {
            total = total + articles[i].getPrice();
        }
        return total;
    }

    public void setTotalCartPrice(double totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }

    public User getUser() {
        return user;

    }

    public void setUser(User user) {
        this.user = user;
    }
}
