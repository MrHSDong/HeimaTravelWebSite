package cn.itheima.travel.domain;

import java.util.List;

public class Route {
    private int rid;
    private String rname;
    private double price;
    private String routeIntroduce;
    private String rflag ;
    private String rdate;
    private String isThemeTour;
    private int count;
    private int cid;
    private String rimage;
    private int sid;
    private String sourcedId;

    private List<RouteImg> routeImgs;
    private Seller seller;
    private Category category;

    public List<RouteImg> getRouteImgs() {
        return routeImgs;
    }

    public void setRouteImgs(List<RouteImg> routeImgs) {
        this.routeImgs = routeImgs;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRouteIntroduce() {
        return routeIntroduce;
    }

    public void setRouteIntroduce(String routeIntroduce) {
        this.routeIntroduce = routeIntroduce;
    }

    public String getRflag() {
        return rflag;
    }

    public void setRflag(String rflag) {
        this.rflag = rflag;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getIsThemeTour() {
        return isThemeTour;
    }

    public void setIsThemeTour(String isThemeTour) {
        this.isThemeTour = isThemeTour;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getRimage() {
        return rimage;
    }

    public void setRimage(String rimage) {
        this.rimage = rimage;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSourcedId() {
        return sourcedId;
    }

    public void setSourcedId(String sourcedId) {
        this.sourcedId = sourcedId;
    }

    @Override
    public String toString() {
        return "Route{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                ", price=" + price +
                ", routeIntroduce='" + routeIntroduce + '\'' +
                ", rflag='" + rflag + '\'' +
                ", rdate='" + rdate + '\'' +
                ", isThemeTour='" + isThemeTour + '\'' +
                ", count=" + count +
                ", cid=" + cid +
                ", rimage='" + rimage + '\'' +
                ", sid=" + sid +
                ", sourcedId='" + sourcedId + '\'' +
                ", routeImgs=" + routeImgs +
                ", seller=" + seller +
                ", category=" + category +
                '}';
    }
}
