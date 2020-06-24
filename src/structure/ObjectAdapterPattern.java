package structure;

/**
 * 适配器模式之对象适配器
 */
public class ObjectAdapterPattern {
    public static void main(String[] args) {
        Client client = new Client(new Adapter());
        client.doThings();
    }

}

/**
 * 抽象目标类（ITarget）该类由于某些特殊原因，Client类无法直接调用该接口以及其已有子类的方法
 */
interface ITarget {
    void dealThings();
}

/**
 * 具体目标类（ConcreteTarget）该类由于某些特殊原因，Client类无法直接调用该类
 */
class ConcreteTarget implements ITarget{

    @Override
    public void dealThings() {
        System.out.println("deal real things");
    }
}

/**
 * 抽象适配器类(IAdapter)Client类可以访问和调用的接口
 */
interface IAdapter{
    public void doSomethings();
}

/**
 * 具体适配器类(Adapter)通过组合的方式Adapter持有一个ITarget对象
 */
class Adapter implements IAdapter{
    private ITarget target;

    public Adapter(){
        this.target = new ConcreteTarget();
    }

    @Override
    public void doSomethings() {
        System.out.println("I am an adapter");
        this.target.dealThings();
    }
}

/**
 * 该类由于特殊原因，只能访问IAdapter及其子类的方法。
 */
class Client{
    IAdapter adapter;

    public Client(IAdapter adapter){
        this.adapter = adapter;
    }

    public void doThings(){
        adapter.doSomethings();
    }
}


