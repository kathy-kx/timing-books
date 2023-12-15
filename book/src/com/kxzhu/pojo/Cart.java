package com.kxzhu.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象。
 * 包含购物车所有商品的总数量totalCount、所有商品总金额totalPrice、购物车所有商品项items
 * 以及Cart的功能方法：addItem、deleteItem、clear、updateCount
 * @ClassName Cart
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-17 15:17
 */
public class Cart {
    //private Integer totalCount;                                     //总商品数量
    //private BigDecimal totalPrice;                                  //总商品金额

    /*
    items:Map对象。代表多个商品。
    key:商品编号，id
    value:商品项，CartItem对象。有其id,name,count,price,totalPrice
     */
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();  //购物车商品

    //Cart的功能方法

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        // 先查看购物车中是否已经添加过此商品，如果已添加，则数量累加，总金额更新。如果没有添加过，直接放到集合中即可
        CartItem item = items.get(cartItem.getId());//Map的查找方法 map.get(key):获取指定key对应的value
        //此item即，购物车的某一个商品，其id 与方法参数cartItem的id相同

        if(item == null){
            //购物车内没有 这个待添加商品cartItem
            items.put(cartItem.getId(),cartItem);
        }else {
            //购物车内已有 这个待添加商品cartItem
            item.setCount(item.getCount() + 1);//将该商品的数量 在原来数量基础上+1
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//将该商品的总金额更新:单价*商品数量
        }

    }

    /**
     * 删除商品项(不是数量-1)
     */
    public void deleteItem(Integer id){
        items.remove(id); //Map的方法remove()
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();//Map的clear方法
    }

    /**
     * 修改某个商品数量（件数）
     */
    public void updateCount(Integer id, Integer count){
        //先查看购物车中是否有此商品。如果有，修改商品数量，更新总金额
        CartItem cartItem = items.get(id);
        if (cartItem != null){
            //找到了。购物车中有此商品
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }


    //getter&setter
    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {   //items是一个map，key为id，值为CartItem对象
            totalCount += entry.getValue().getCount();  //把items中每一项的商品数量 累加到totalCount上
        }
        return totalCount;
    }

    //TotalCount不应该通过set方法赋值，而应该累加购物车的商品数量。所以此方法不能这样用。而应该在getTotalCount中累加
    //public void setTotalCount(Integer totalCount) {
    //    this.totalCount = totalCount;
    //}

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }

    //TotalPrice不应该通过set方法赋值，而应该累加购物车的商品价格。所以此方法不能这样用。而应该在getTotalPrice中累加
    //public void setTotalPrice(BigDecimal totalPrice) {
    //    this.totalPrice = totalPrice;
    //}

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
