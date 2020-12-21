package cn.itheima.travel.domain;

public class RouteImg {
    private int rgid;
    private int rid;
    private String bigpic;
    private String smallpic;

    public int getRgid() {
        return rgid;
    }

    public void setRgid(int rgid) {
        this.rgid = rgid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }



    public String getBigpic() {
        return bigpic;
    }

    public void setBigpic(String bigPic) {
        this.bigpic = bigPic;
    }

    public String getSmallpic() {
        return smallpic;
    }

    public void setSmallpic(String smallPic) {
        this.smallpic = smallPic;
    }

    @Override
    public String toString() {
        return "RouteImg{" +
                "rgid=" + rgid +
                ", rid=" + rid +
                ", bigPic='" + bigpic + '\'' +
                ", smallPic='" + smallpic + '\'' +
                '}';
    }
}
