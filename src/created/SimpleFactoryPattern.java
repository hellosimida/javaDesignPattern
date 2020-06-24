package created;

/**
 * 简单工厂模式
 * 简单地说，简单工厂模式通常就是这样，一个工厂类 XxxFactory，里面有一个静态方法，根据我们不同的参数，返回不同的派生自同一个父类（或实现同一接口）的实例对象。
 * 我们强调职责单一原则，一个类只提供一种功能，FoodFactory 的功能就是只要负责生产各种 Food。
 */
public class SimpleFactoryPattern {
    public static void main(String[]args){
        ComputerFactory computerFactory =  new ComputerFactory();
        computerFactory.createComputer("hp").start();
    }

}

abstract class Computer1 {
    /**
     * 产品的抽象方法，由具体的产品类去实现
     */
    public abstract void start();
}

class LenovoComputer extends Computer1 {
    @Override
    public void start() {
        System.out.println("联想电脑启动");
    }
}

class HpComputer extends Computer1{
    @Override
    public void start() {
        System.out.println("惠普电脑启动");
    }
}

class AsusComputer extends Computer1 {
    @Override
    public void start() {
        System.out.println("华硕电脑启动");
    }
}

class ComputerFactory {
    public Computer1 createComputer(String type){
        Computer1 mComputer=null;
        switch (type) {
            case "lenovo":
                mComputer=new LenovoComputer();
                break;
            case "hp":
                mComputer=new HpComputer();
                break;
            case "asus":
                mComputer=new AsusComputer();
                break;

        }
        return mComputer;
    }
}
