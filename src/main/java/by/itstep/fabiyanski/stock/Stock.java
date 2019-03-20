package by.itstep.fabiyanski.stock;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private String address;
    private List<GoodsItem> stockGoodsItems;


    public Stock(String address) {
        this.address = address;
        this.stockGoodsItems = new ArrayList<GoodsItem>();
    }

    public List<GoodsItem> getStockGoodsItems() {
        return stockGoodsItems;
    }

    public void setStockGoodsItems(List<GoodsItem> stockGoodsItems) {
        this.stockGoodsItems = stockGoodsItems;
    }
}
