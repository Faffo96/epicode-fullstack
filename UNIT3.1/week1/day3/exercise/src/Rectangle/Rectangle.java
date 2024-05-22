package Rectangle;

public class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double calculatePerimeter(double width, double height) {
        return 2 * (width + height);
    }

    public double calculateArea(double width, double height) {
        return width * height;
    }

    public void getRectangle(Rectangle rectangle) {
        double area = calculateArea(rectangle.width, rectangle.height);
        double perimeter = calculatePerimeter(rectangle.width, rectangle.height);
        System.out.println("The area of this rectangle is: " + area + ". The perimeter is: " + perimeter);
    }

    public void get2Rectangles(Rectangle rectangle1, Rectangle rectangle2) {
        getRectangle(rectangle1);
        getRectangle(rectangle2);

        double totalArea = calculateArea(rectangle1.width, rectangle1.height) + calculateArea(rectangle2.width, rectangle2.height);
        double totalPerimeter = calculatePerimeter(rectangle1.width, rectangle1.height) + calculatePerimeter(rectangle2.width, rectangle2.height);
        System.out.println("The sum of the two areas is: " + totalArea + ". The sum of the perimeters is: " + totalPerimeter);
    }
}
