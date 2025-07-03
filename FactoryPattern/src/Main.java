public class Main {
    public static void main(String[] args) {

        String input = "CIRCLE";
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.getShape(input);
        shape.draw();
    }
}