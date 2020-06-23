package created;

public class SimpleFactoryMode {
    public abstract class Computer {
        /**
         * 产品的抽象方法，由具体的产品类去实现
         */
        public abstract void start();
    }

    public class LenovoComputer extends Computer {
        @Override
        public void start() {
            System.out.println("联想电脑启动");
        }
    }

    public class HpComputer extends Computer{
        @Override
        public void start() {
            System.out.println("惠普电脑启动");
        }
    }

    public class AsusComputer extends Computer {
        @Override
        public void start() {
            System.out.println("华硕电脑启动");
        }
    }

    public class ComputerFactory {
        public Computer createComputer(String type){
            Computer mComputer=null;
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

    public static void main(String[]args){
        SimpleFactoryMode simpleFactoryMode = new SimpleFactoryMode();
        ComputerFactory computerFactory = simpleFactoryMode.new ComputerFactory();
        computerFactory.createComputer("hp").start();
    }
}
