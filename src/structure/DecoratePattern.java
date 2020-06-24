package structure;

/**
 * 装饰模式
 * 1. 何时使用
 * 在不想增加很多子类的情况下扩展类时
 * 2. 方法
 * 将具体功能职责划分，同时继承装饰者模式
 * 3. 优点
 * 装饰类和被装饰类可以独立发展，而不会相互耦合。它有效地把类的核心职责和装饰功能分开了
 * 装饰模式是继承关系的一个替代方案
 * 装饰模式可以动态地扩展一个实现类的功能
 * 4. 缺点
 * 多层装饰比较复杂。比如我们现在有很多层装饰，出了问题，一层一层检查，最后发现是最里层的装饰出问题了，想想工作量都害怕
 * 5. 使用场景
 * 需要扩展一个类的功能时
 * 需要动态地给一个对象增加功能，并可以动态地撤销时
 * 需要为一批的兄弟类进行改装或加装功能时
 *
 *
 * 平常当系统需要新功能时，是向旧的类中添加新的代码，这些新加的代码通常装饰了原有类的核心职责或主要行为，这种做法的问题在于，它们再主类中加入了新的字段、新的方法和新的逻辑，从而增加了主类的复杂度，而这些新加入的东西仅仅是为了满足一些只在某种特定情况下才会执行的个特殊行为的需要。
 * 而装饰模式却提供了一个非常好的解决方案，它把每个要装饰的功能放在单独的类中，并让这个类包装它所要装饰的对象。因此当需要执行特殊行为时，客户代码就可以在运行时根据需要有选择地、按顺序的地使用装饰功能包装对象了。
 *
 *
 * JAVA IO就是典型的装饰模式
 * InputStream就是最原始的对象
 * ByteArrayInputStream、FileInputStream、PipedInputStream都是继承自InputStream，属于最基础的输入流
 * FilterInputStream继承自InputStream,是装饰模式的关键节点，其实现类是一系列装饰器
 * BufferedInputStream继承自FilterInputStream，代表用缓冲来装饰，也就使得输入流具有了缓冲的功能，
 * LineNumberInputStream继承自FilterInputStream，代表用行号来装饰，在操作的时候就可以取得行号了，
 * DataInputStream继承自FilterInputStream，使得我们可以从输入流转换为 java 中的基本类型值。
 * 获取行号就通过一层层的修饰器得到最终结果
 * InputStream inputStream = new LineNumberInputStream(new BufferedInputStream(new FileInputStream("")));
 *
 */
public class DecoratePattern {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        //第一次修饰
        component = new ConcreteDecoratorA(component);
        //第二次修饰
        component = new ConcreteDecoratorB(component);
        //修饰后运行
        component.operation();
    }
}

/**
 * Component是一个接口或是抽象类，就是定义我们最核心的对象，也就是最原始的对象。
 */
abstract class Component {
    public abstract void operation();
}

/**
 * 具体构件，通过继承实现Component抽象类中的抽象方法。是最核心、最原始、最基本的接口或抽象类的实现，我们要装饰的就是它。
 */
class ConcreteComponent extends Component {

    @Override
    public void operation() {
        System.out.println("具体对象的操作");
    }

}

/**
 * 装饰类
 * 一般是一个抽象类，在其属性里必然有一个private变量指向Component抽象构件。
 */
abstract class Decorator extends Component {

    private Component component = null;

    //通过构造函数传递给被修饰者
    public Decorator(Component component) {
        this.component = component;
    }

    //委托给被修饰者执行
    @Override
    public void operation() {
        if(component != null) {
            this.component.operation();
        }
    }

}

/**
 * 我们可以写多个具体实现类，把最核心的、最原始的、最基本的东西装饰成其它东西。
 * 这里就写两个类，稍改一下二者的实现顺序，看看结果。
 */

/**
 * 装饰类的具体实现A
 */
class ConcreteDecoratorA extends Decorator {

    //定义被修饰者
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    //定义自己的修饰方法
    private void method1() {
        System.out.println("method1 修饰");
    }

    @Override
    public void operation() {
        this.method1();
        super.operation();
    }

}

/**
 * 装饰类B
 */
class ConcreteDecoratorB extends Decorator {

    //定义被修饰者
    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    //定义自己的修饰方法
    private void method2() {
        System.out.println("method2 修饰");
    }

    @Override
    public void operation() {
        super.operation();
        this.method2();
    }

}