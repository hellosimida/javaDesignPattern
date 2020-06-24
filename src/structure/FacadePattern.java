package structure;

/**
 * 门面模式
 * 也叫外观模式（Facade Pattern）在许多源码中有使用，比如 slf4j 就可以理解为是门面模式的应用。这是一个简单的设计模式
 */
public class FacadePattern {
    public static void main(String[] args) {
        /**
         * 常规使用方法，创建各自的对象，再画图
         */
        // 画一个圆形
        Shape circle = new Circle();
        circle.draw();

        // 画一个长方形
        Shape rectangle = new Rectangle();
        rectangle.draw();

        /**
         * 使用门面类直接可以画出想画的图
         */
        ShapeMaker shapeMaker = new ShapeMaker();

        // 客户端调用现在更加清晰了
        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}

/**
 * 定义一个基础接口画图
 */
interface Shape {
    void draw();
}

/**
 *画圆
 */
class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("画圆");
    }
}

/**
 *画矩形
 */
class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("画矩形");
    }
}

/**
 *画正方形
 */
class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("画正方形");
    }
}

/**
 * 定义一个门面类，将所有的对象都创建出来
 */
class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    /**
     * 下面定义一堆方法，具体应该调用什么方法，由这个门面来决定
     */

    public void drawCircle(){
        circle.draw();
    }
    public void drawRectangle(){
        rectangle.draw();
    }
    public void drawSquare(){
        square.draw();
    }
}

