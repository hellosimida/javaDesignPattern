package structure;

/**
 * 适配器模式之默认适配器
 */
public class DefaultAdapterPattern {
    public static void main(String[] args) {

        CarDesigner carDesigner=new CarDesigner();
        carDesigner.drawPicture();
        carDesigner.findIdea();
        carDesigner.thinkUE();
        carDesigner.showPicture();

        System.out.println("----------------------");

        HouseDesigner houseDesigner=new HouseDesigner();
        houseDesigner.drawPicture();
        houseDesigner.findIdea();
        houseDesigner.thinkUE();
        houseDesigner.showPicture();
    }
}

interface IDesigner {

    public void drawPicture();

    public void findIdea();

    public void thinkUE();

    public void showPicture();
}

//默认适配器
abstract class Designer implements IDesigner {

    public void drawPicture() {
        System.out.println("画画！");
    }

    public void findIdea() {
        System.out.println("寻找灵感！");
    }
}

//适配器1
class CarDesigner extends Designer{

    @Override
    public void thinkUE() {
        System.out.println("思考汽车的用户体验！");
    }

    @Override
    public void showPicture() {
        System.out.println("办汽车设计展！");
    }

}

//适配器2
class HouseDesigner extends Designer {

    @Override
    public void thinkUE() {
        System.out.println("思考房屋的用户体验！");
    }

    @Override
    public void showPicture() {
        System.out.println("办房屋设计展！");
    }
}