package Product;
import Customer.Customer;
import Enum.Category;
import Enum.Tier;
import Order.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product(1L, "Book 1", Category.BOOKS, 20.0);
        Product p2 = new Product(2L, "Book 2", Category.BOOKS, 25.0);
        Product p3 = new Product(3L, "Book 3", Category.BOOKS, 18.0);
        Product p4 = new Product(4L, "Book 4", Category.BOOKS, 110.0);
        Product p5 = new Product(5L, "Book 5", Category.BOOKS, 28.0);
        Product p6 = new Product(6L, "Book 6", Category.BOOKS, 22.0);
        Product p7 = new Product(7L, "Book 7", Category.BOOKS, 120.0);
        Product p8 = new Product(8L, "Book 8", Category.BOOKS, 35.0);
        Product p9 = new Product(9L, "Book 9", Category.BOOKS, 45.0);
        Product p10 = new Product(10L, "Book 10", Category.BOOKS, 100.0);

        Product p11 = new Product(11L, "Toy 1", Category.BABY, 15.0);
        Product p12 = new Product(12L, "Toy 2", Category.BABY, 18.0);
        Product p13 = new Product(13L, "Toy 3", Category.BABY, 22.0);
        Product p14 = new Product(14L, "Clothing 1", Category.BABY, 125.0);
        Product p15 = new Product(15L, "Clothing 2", Category.BABY, 25.0);
        Product p16 = new Product(16L, "Blanket", Category.BABY, 20.0);
        Product p17 = new Product(17L, "Bottle", Category.BABY, 8.0);
        Product p18 = new Product(18L, "Diaper", Category.BABY, 12.0);
        Product p19 = new Product(19L, "Pacifier", Category.BABY, 5.0);
        Product p20 = new Product(20L, "Stroller", Category.BABY, 150.0);

        Product p21 = new Product(21L, "Action Figure", Category.BOYS, 12.0);
        Product p22 = new Product(22L, "Remote Control Car", Category.BOYS, 30.0);
        Product p23 = new Product(23L, "Superhero Costume", Category.BOYS, 130.0);
        Product p24 = new Product(24L, "Toy Gun", Category.BOYS, 18.0);
        Product p25 = new Product(25L, "Soccer Ball", Category.BOYS, 20.0);
        Product p26 = new Product(26L, "Baseball Glove", Category.BOYS, 40.0);
        Product p27 = new Product(27L, "Video Game Console", Category.BOYS, 400.0);
        Product p28 = new Product(28L, "Remote Control Helicopter", Category.BOYS, 160.0);
        Product p29 = new Product(29L, "Lego Set", Category.BOYS, 95.0);
        Product p30 = new Product(30L, "Toy Sword", Category.BOYS, 15.0);

        List<Product> productList = new ArrayList<>();
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
        productList.add(p4);
        productList.add(p5);
        productList.add(p6);
        productList.add(p7);
        productList.add(p8);
        productList.add(p9);
        productList.add(p10);
        productList.add(p11);
        productList.add(p12);
        productList.add(p13);
        productList.add(p14);
        productList.add(p15);
        productList.add(p16);
        productList.add(p17);
        productList.add(p18);
        productList.add(p19);
        productList.add(p20);
        productList.add(p21);
        productList.add(p22);
        productList.add(p23);
        productList.add(p24);
        productList.add(p25);
        productList.add(p26);
        productList.add(p27);
        productList.add(p28);
        productList.add(p29);
        productList.add(p30);

        Customer c1 = new Customer(101L, "Mario", Tier.TIER1);
        Customer c2 = new Customer(102L, "Giovanna", Tier.TIER1);
        Customer c3 = new Customer(103L, "Paolo", Tier.TIER2);
        Customer c4 = new Customer(104L, "Antonio", Tier.TIER2);

        List<Customer> customerList = new ArrayList<>();

        customerList.add(c1);
        customerList.add(c2);
        customerList.add(c3);
        customerList.add(c4);

        Order o1 = new Order(201L, "DELIVERED", c1);
        Order o2 = new Order(201L, "DELIVERED", c2);
        Order o3 = new Order(201L, "DELIVERED", c3);
        Order o4 = new Order(201L, "DELIVERED", c4);

        o1.setProducts(new ArrayList<>(List.of(p1, p2, p4, p12, p5, p22)));
        o2.setProducts(new ArrayList<>(List.of(p3, p24, p21, p8, p22, p30, p9, p12)));
        o3.setProducts(new ArrayList<>(List.of(p17, p6, p9, p28, p16, p3)));
        o4.setProducts(new ArrayList<>(List.of(p19, p13, p11, p29, p30, p7, p4)));

        List<Order> orderList = new ArrayList<>();

        orderList.add(o1);
        orderList.add(o2);
        orderList.add(o3);
        orderList.add(o4);

        System.out.println("BOOKS products that costs more than 100:");

        List<Product> expensiveBooks = productList.stream().filter(product -> product.getCategory() == Category.BOOKS && product.getPrice() > 100).toList();

        for (Product product : expensiveBooks) {
            System.out.println(product);
        }

        System.out.println();

        System.out.println("BABY order's products:");

        List<Product> babyProducts = orderList.stream()
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getCategory() == Category.BABY)
                .toList();

        for (Product product : babyProducts) {
            System.out.println(product);
        }

        System.out.println();

        System.out.println("BOYS products with 10% discount:");

        /*List<Product> boysDiscountedProducts = productList.stream().filter(product -> product.getCategory() == Category.BOYS).*/

        List<Product> boysDiscountedProducts = productList.stream().filter(product -> product.getCategory() == Category.BOYS).map(product -> {
            double discountedPrice = product.getPrice() * 0.9;
            return new Product(product.getId(), product.getName(), product.getCategory(), discountedPrice);
        }).toList();

        for (Product product : boysDiscountedProducts) {
            System.out.println(product);
        }

        System.out.println();

        System.out.println("Tier 2 Customer's order's products:");

        LocalDate startDate = LocalDate.of(2024, 4, 22);
        LocalDate endDate = LocalDate.of(2024, 4, 28);

        List<Product> tier2Products = orderList.stream()
                .filter(order -> order.getCustomer().getTier() == Tier.TIER2)
                .filter(order -> order.getOrderDate().isAfter(startDate))
                .filter(order -> order.getDeliveryDate().isBefore(endDate))
                .flatMap(order -> order.getProducts().stream())
                .toList();

        for (Product product : tier2Products) {
            System.out.println(product);
        }
    }
}