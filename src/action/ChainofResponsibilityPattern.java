package action;

/**
 * 责任链模式
 *
 * 责任链，顾名思义，就是用来处理相关事务责任的一条执行链，执行链上有多个节点，每个节点都有机会（条件匹配）处理请求事务，如果某个节点处理完了就可以根据实际业务需求传递给下一个节点继续处理或者返回处理完毕。
 *
 * 现实中，请假的OA申请，请假天数如果是半天到1天，可能直接主管批准即可；
 * 如果是1到3天的假期，需要部门经理批准；
 * 如果是3天到30天，则需要总经理审批；
 * 大于30天，正常不会批准。
 *
 * 为了实现上述场景，我们可以采用责任链设计模式。
 * 员工提交请求类：LeaveRequest。
 * 抽象的请假责任处理类：AbstractLeaveHandler。
 * 直接主管审批处理类：DirectLeaderLeaveHandler。
 * 部门经理处理类：DeptManagerLeaveHandler。
 * 总经理处理类： GManagerLeaveHandler。
 */
public class ChainofResponsibilityPattern {
    public static void main(String[] args) {
        LeaveRequest request = LeaveRequest.builder().leaveDays(20).name("小明").build();


        AbstractLeaveHandler directLeaderLeaveHandler = new DirectLeaderLeaveHandler("县令");
        DeptManagerLeaveHandler deptManagerLeaveHandler = new DeptManagerLeaveHandler("知府");
        GManagerLeaveHandler gManagerLeaveHandler = new GManagerLeaveHandler("京兆尹");

        directLeaderLeaveHandler.setNextHandler(deptManagerLeaveHandler);
        deptManagerLeaveHandler.setNextHandler(gManagerLeaveHandler);

        directLeaderLeaveHandler.handlerRequest(request);


    }
}

/**
 * 员工提交请求实体类
 */
class LeaveRequest {
    /**天数*/
    private int leaveDays;

    /**姓名*/
    private String name;

    public LeaveRequest(int leaveDays, String name) {
        this.leaveDays = leaveDays;
        this.name = name;
    }

    public LeaveRequest() {

    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // build() 方法负责将 UserBuilder 中设置好的属性“复制”到 User 中。
    // 当然，可以在 “复制” 之前做点检验
    public LeaveRequest build() {
        return new LeaveRequest(leaveDays, name);
    }

    public static LeaveRequest builder() {
        return new LeaveRequest();
    }

    public LeaveRequest name(String name) {
        this.name = name;
        return this;
    }

    public LeaveRequest leaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
        return this;
    }

}

/**
 * 抽象的请假责任处理类
 */
class AbstractLeaveHandler {
    /**直接主管审批处理的请假天数*/
    protected int MIN = 1;
    /**部门经理处理的请假天数*/
    protected int MIDDLE = 3;
    /**总经理处理的请假天数*/
    protected int MAX = 30;

    /**领导名称*/
    protected String handlerName;

    /**下一个处理节点（即更高级别的领导）*/
    protected AbstractLeaveHandler nextHandler;

    /**设置下一节点*/
    protected void setNextHandler(AbstractLeaveHandler handler){
        this.nextHandler = handler;
    }

    /**处理请假的请求，子类实现*/
    protected void handlerRequest(LeaveRequest request){

    }
}

/**
 * 直接主管审批处理类
 */
class DirectLeaderLeaveHandler extends AbstractLeaveHandler{
    public DirectLeaderLeaveHandler(String name) {
        this.handlerName = name;
    }

    @Override
    protected void handlerRequest(LeaveRequest request) {
        if(request.getLeaveDays() <= this.MIN){
            System.out.println("直接主管:" + handlerName + ",已经处理;流程结束。");
            return;
        }

        if(null != this.nextHandler){
            this.nextHandler.handlerRequest(request);
        }else{
            System.out.println("审批拒绝！");
        }

    }
}

/**
 * 部门经理处理类
 */
class DeptManagerLeaveHandler extends AbstractLeaveHandler {

    public DeptManagerLeaveHandler(String name) {
        this.handlerName = name;
    }

    @Override
    protected void handlerRequest(LeaveRequest request) {
        if(request.getLeaveDays() >this.MIN && request.getLeaveDays() <= this.MIDDLE){
            System.out.println("部门经理:" + handlerName + ",已经处理;流程结束。");
            return;
        }

        if(null != this.nextHandler){
            this.nextHandler.handlerRequest(request);
        }else{
            System.out.println("审批拒绝！");
        }
    }
}

/**
 * 总经理处理类
 */
class GManagerLeaveHandler extends AbstractLeaveHandler {
    public GManagerLeaveHandler(String name) {
        this.handlerName = name;
    }

    @Override
    protected void handlerRequest(LeaveRequest request) {
        if(request.getLeaveDays() > this.MIDDLE && request.getLeaveDays() <= this.MAX){
            System.out.println("总经理:" + handlerName + ",已经处理;流程结束。");
            return;
        }

        if(null != this.nextHandler){
            this.nextHandler.handlerRequest(request);
        }else{
            System.out.println("审批拒绝！");
        }
    }
}
