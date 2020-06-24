package structure;

/**
 * ÀàÊÊÅäÆ÷Ä£Ê½
 */
public class ClassAdapterPattern {
    public static void main(String[] args) {
        DC5 dc5 = new PowerAdapter();
        dc5.output5V();
    }
}

class AC220 {
    public int output220V(){
        int output = 220;
        return output;
    }
}

interface DC5 {
    int output5V();
}

class PowerAdapter extends AC220 implements DC5 {
    @Override
    public int output5V() {
        int output = output220V();
        return (output / 44);
    }
}
