package structure;

/**
 * 桥梁模式
 * 定义: 将抽象和实现解耦, 使得两者可以独立的变化
 * 通俗的说, 就是一个类调用另一个类中的方法, 需要一个桥梁, 通过聚合的关系调用
 *
 * 桥梁模式的优点:
 * 抽象和实现分离. 这是桥梁模式的主要特点, 它完全是为了解决继承的缺点而提出的设计模式. 在该模式下,实现可以不受抽象的约束,不用再绑定在一个固定的抽象层次上
 * 优秀的扩充能力.
 * 实现细节对客户透明. 客户不用关心细节的实现, 它已经由抽象层通过聚合关系完成了封装.
 *
 * 桥梁模式的使用场景:
 * 不希望或不适用使用继承的场景. 例如继承层次过滤、无法更细化设计颗粒等场景
 * 接口或抽象类不稳定的场景.
 * 重用性要求较高的场景. 设计的颗粒度越细,则被重用的可能性就越大, 而采用继承则受父类的限制, 不可能出现太细的颗粒度
 *
 *
 * 使用桥梁模式主要考虑如何拆分抽象和实现,并不是一设计继承就要考虑使用该模式. 桥梁模式的意图还是对变化的封装, 尽量把可能变化的因素封装到最细、最小的逻辑单元中,避免风险扩散.因此在进行系统设计时,发现类的继承有N层时,可以考虑使用桥梁模式
 * 桥梁模式在Java应用中的一个非常典型的例子就是JDBC驱动器。JDBC为所有的关系型数据库提供一个通用的界面。一个应用系统动态地选择一个合适的驱动器，然后通过驱动器向数据库引擎发出指令。这个过程就是将抽象角色的行为委派给实现角色的过程。
 * 抽象角色可以针对任何数据库引擎发出查询指令，因为抽象角色并不直接与数据库引擎打交道，JDBC驱动器负责这个底层的工作。由于JDBC驱动器的存在，应用系统可以不依赖于数据库引擎的细节而独立地演化；同时数据库引擎也可以独立于应用系统的细节而独立的演化
 */
public class BridgePattern {
    /**
     * ********************************************************
     * @ClassName: Client
     * @Description: 桥梁模式测试客户端
     *
     **********************************************************
     */
    public static void main(String[] args) {
        DriverManager manager = new DriverManager(new MysqlDriver());
        manager.getConnection();

        manager = new DriverManager(new OracleDriver());
        manager.getConnection();
    }
}

/**
 * ********************************************************
 * @ClassName: Driver
 * @Description: 实现化角色
 *
 **********************************************************
 */
interface Driver {
    public void getConnection();
}

/**
 * ********************************************************
 * @ClassName: MysqlDriver
 * @Description: 具体实现化角色
 *
 **********************************************************
 */
class MysqlDriver implements Driver{
    @Override
    public void getConnection() {
        System.out.println("mysql 数据库连接");
    }
}

/**
 * ********************************************************
 * @ClassName: OracleDriver
 * @Description: 具体实现化角色
 *
 **********************************************************
 */
class OracleDriver implements Driver{
    @Override
    public void getConnection() {
        System.out.println("oracle数据库连接");
    }
}

/**
 * ********************************************************
 * @ClassName: Manager
 * @Description: 抽象化角色
 *
 **********************************************************
 */
abstract class Manager {
    private Driver driver;

    public Manager(Driver driver){
        this.driver = driver;
    }

    public  void getConnection(){
        driver.getConnection();
    }
}
/**
 * ********************************************************
 * @ClassName: Manager
 * @Description: 具体抽象化角色
 *
 **********************************************************
 */
class DriverManager extends Manager {
    public DriverManager(Driver driver){
        super(driver);
    }

    @Override
    public  void getConnection(){
        super.getConnection();
    }
}

