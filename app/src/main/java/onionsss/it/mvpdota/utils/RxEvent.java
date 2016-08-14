package onionsss.it.mvpdota.utils;

/**
 * 作者：张琦 on 2016/8/11 18:46
 */
public class RxEvent {

    private String name;
    private int type;

    public RxEvent(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
