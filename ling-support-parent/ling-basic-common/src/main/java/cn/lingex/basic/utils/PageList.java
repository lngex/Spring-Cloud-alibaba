package cn.lingex.basic.utils;


import java.util.ArrayList;
import java.util.List;

/**
 * @author 廖建波
 * 分页对象：easyui只需两个属性，count(总数),content（分页数据）就能实现分页
 */
public class PageList<T> {
    private long total;
    private List<T> content = new ArrayList<>();

    public long getTotal() {
        return total;
    }

    public PageList<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public List<T> getContent() {
        return content;
    }

    public PageList<T> setContent(List<T> content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        return "PageList{" +
                "total=" + total +
                ", rows=" + content +
                '}';
    }

    public PageList(long total, List<T> rows) {
        this.total = total;
        this.content = rows;
    }
    public PageList() {
    }
}
