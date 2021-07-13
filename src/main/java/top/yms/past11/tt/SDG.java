package top.yms.past11.tt;

class Message {
    private Integer type;
    private Object data;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}



public class SDG {
    public static void main(String[] args) {
        Message message = new Message();
        message.setType(300);



    }
}
