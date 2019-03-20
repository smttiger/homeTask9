package by.itstep.fabiyanski.stock;

import by.itstep.fabiyanski.stock.Exceptions.GoodsItemAmountIsNotEnoughException;
import by.itstep.fabiyanski.stock.Exceptions.GoodsItemDoesntExistsException;

import java.util.List;

public interface StockService {

    void takeTheGoodsFromProvisioner(Stock stock, Waybill waybill);//принять товар от поставщика

    void takeTheGoodsFromStockToStock(Stock stock1, Stock stock2, Waybill waybill) throws GoodsItemDoesntExistsException,
            GoodsItemAmountIsNotEnoughException;

    void printListOfAllGoods(Stock stock);

    void printListOfAllProvisioners();

    List<GoodsItem> search(Stock stock, String name) throws GoodsItemDoesntExistsException;
    // товары могут быть с одинаковым наименованием, но с разной входной ценой,
    //поэтому в этом методе я возвращаю список таких товаров
}
