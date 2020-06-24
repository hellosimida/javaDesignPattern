package created;

/**
 * 抽象工厂模式
 * 抽象工厂的问题是显而易见的，比如我们要加个显示器，就需要修改所有的工厂，给所有的工厂都加上制造显示器的方法。这有点违反了对修改关闭，对扩展开放这个设计原则。
 */
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        // 第一步就要选定一个“大厂”
        ComputerFactory1 cf = new AmdFactory();
        // 从这个大厂造 CPU
        CPU cpu = cf.makeCPU();
        // 从这个大厂造主板
        MainBoard board = cf.makeMainBoard();
        // 从这个大厂造硬盘
        HardDisk hardDisk = cf.makeHardDisk();

        // 将同一个厂子出来的 CPU、主板、硬盘组装在一起
        Computer2 result = new Computer2(cpu, board, hardDisk);
    }
}
abstract class ComputerFactory1 {
    /**
     * 制造cpu
     */
    public abstract CPU makeCPU();

    /**
     * 制造主板
     */
    public abstract MainBoard makeMainBoard();
    /**
     * 制造硬盘
     */
    public abstract HardDisk makeHardDisk();
}

class AmdFactory extends ComputerFactory1{

    @Override
    public CPU makeCPU() {
        CPU cpu = new CPU();
        cpu.setBrand("amdCPU");
        System.out.println("amd制造CPU");
        return cpu;
    }

    @Override
    public MainBoard makeMainBoard() {
        MainBoard mainBoard = new MainBoard();
        mainBoard.setBrand("amd主板");
        System.out.println("amd制造主板");
        return mainBoard;
    }

    @Override
    public HardDisk makeHardDisk() {
        HardDisk hardDisk = new HardDisk();
        hardDisk.setBrand("amd硬盘");
        System.out.println("amd制造硬盘");
        return hardDisk;
    }
}

class IntelFactory extends ComputerFactory1{

    @Override
    public CPU makeCPU() {
        CPU cpu = new CPU();
        cpu.setBrand("intelCPU");
        System.out.println("intel制造CPU");
        return cpu;
    }

    @Override
    public MainBoard makeMainBoard() {
        MainBoard mainBoard = new MainBoard();
        mainBoard.setBrand("intel主板");
        System.out.println("intel制造主板");
        return mainBoard;
    }

    @Override
    public HardDisk makeHardDisk() {
        HardDisk hardDisk = new HardDisk();
        hardDisk.setBrand("intel硬盘");
        System.out.println("intel制造硬盘");
        return hardDisk;
    }
}

class Computer2{
    public Computer2(CPU cpu, MainBoard board, HardDisk hardDisk) {
        System.out.println("电脑组装cpu:"+cpu.getBrand()+",主板："+board.getBrand()+",硬盘："+hardDisk.getBrand());
    }
}

class CPU{
    /**
     * 品牌
     */
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

class MainBoard{
    /**
     * 品牌
     */
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
class HardDisk{
    /**
     * 品牌
     */
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
