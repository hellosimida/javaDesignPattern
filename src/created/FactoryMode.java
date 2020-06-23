package created;

/**
 * 工厂模式
 * 简单工厂模式很简单，如果它能满足我们的需要，我觉得就不要折腾了。之所以需要引入工厂模式，是因为我们往往需要使用两个或两个以上的工厂。
 * 虽然都是调用 createComputer("hp") 制作惠普电脑，但是，不同的工厂生产出来的完全不一样。
 * 第一步，我们需要选取合适的工厂，然后第二步基本上和简单工厂一样。
 * 核心在于，我们需要在第一步选好我们需要的工厂。比如，我们有 LogFactory 接口，实现类有 FileLogFactory 和 KafkaLogFactory，分别对应将日志写入文件和写入 Kafka 中，
 * 显然，我们客户端第一步就需要决定到底要实例化 FileLogFactory 还是 KafkaLogFactory，这将决定之后的所有的操作。
 */
public class FactoryMode {
    public static void main(String[]args){
        ChineseComputerFactory computerFactory = new ChineseComputerFactory();
        computerFactory.createComputer("hp").start();
    }
}

abstract class Computer {
    /**
     * 产品的抽象方法，由具体的产品类去实现
     */
    public abstract void start();
}

class AmericanLenovoComputer extends Computer {
    @Override
    public void start() {
        System.out.println("American联想电脑启动");
    }
}

class AmericanHpComputer extends Computer{
    @Override
    public void start() {
        System.out.println("American惠普电脑启动");
    }
}

class AmericanAsusComputer extends Computer {
    @Override
    public void start() {
        System.out.println("American华硕电脑启动");
    }
}

class ChineseLenovoComputer extends Computer {
    @Override
    public void start() {
        System.out.println("Chinese联想电脑启动");
    }
}

class ChineseHpComputer extends Computer{
    @Override
    public void start() {
        System.out.println("Chinese惠普电脑启动");
    }
}

class ChineseAsusComputer extends Computer {
    @Override
    public void start() {
        System.out.println("Chinese华硕电脑启动");
    }
}

class AmericanComputerFactory {
    public Computer createComputer(String type){
        Computer mComputer=null;
        switch (type) {
            case "lenovo":
                mComputer=new AmericanLenovoComputer();
                break;
            case "hp":
                mComputer=new AmericanHpComputer();
                break;
            case "asus":
                mComputer=new AmericanAsusComputer();
                break;

        }
        return mComputer;
    }
}

class ChineseComputerFactory {
    public Computer createComputer(String type){
        Computer mComputer=null;
        switch (type) {
            case "lenovo":
                mComputer=new ChineseLenovoComputer();
                break;
            case "hp":
                mComputer=new ChineseHpComputer();
                break;
            case "asus":
                mComputer=new ChineseAsusComputer();
                break;

        }
        return mComputer;
    }
}
