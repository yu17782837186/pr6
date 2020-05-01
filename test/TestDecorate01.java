package cn.io.com;

public class TestDecorate01 {
    public static void main(String[] args) {
        //装饰器设计模式  放大声音
        Persion p = new Persion();
        p.say();
        Add add =new Add(p);
        add.say();
    }
}
interface Say {
    void say();
}
class Persion implements Say {
    private int voice = 8;
    @Override
    public void say() {
        System.out.println("人的声音为："+this.getVoice());
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }
}
class Add implements Say {
    private Persion p;

    public Add(Persion p) {
        this.p = p;
    }

    @Override
    public void say() {
        System.out.println("人的声音为："+p.getVoice()*10);
        System.out.println("这是噪音！！！");
    }
}