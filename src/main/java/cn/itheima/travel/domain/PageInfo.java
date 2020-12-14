package cn.itheima.travel.domain;

public class PageInfo {
    private boolean flag;
    private String msg;
    private Object data;

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "flag=" + flag +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
