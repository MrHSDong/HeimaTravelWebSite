package cn.itheima.travel.domain;

import java.util.List;

public class PageBean {
    //当前cid总条目数
    private int totalCount;
    //当前页码
    private int currentPage;
    //当前页包含序列化 Route列表
    private List<String> routeList;
    //当前页显示条数
    private int itemCount;
    //当前页Route的cid
    private int cid;
    //总页数
    private int totalPage;

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", routeList=" + routeList +
                ", itemCount=" + itemCount +
                ", cid=" + cid +
                ", totalPage=" + totalPage +
                '}';
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public PageBean(int cid, int currentPage, int itemCount) {
        this.currentPage = currentPage;
        this.itemCount = itemCount;
        this.cid = cid;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<String> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<String> routeList) {
        this.routeList = routeList;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

}
