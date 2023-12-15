package com.kxzhu.pojo;

import java.util.List;

/**
 * @ClassName Page
 * @Description 分页模型对象
 * @Author zhukexin
 * @Date 2022-10-10 14:26
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;

    private Integer pageNo;                 //当前页码
    private Integer pageTotal;              //总页码
    private Integer pageTotalCount;         //总记录数
    private Integer pageSize = PAGE_SIZE;   //每页显示记录数
    private List<T> items;                  //当前页数据
    private String url;                     //分页条的请求地址

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        /*
        pageNo的数据边界有效性校验：超出范围该如何处理
        每个模块分页都要做，可以写到此方法里。设置pageNo时自动检查
         */
        if(pageNo < 1){
            pageNo = 1;
        }
        if(pageNo > pageTotal){
            pageNo = pageTotal;
        }

        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
