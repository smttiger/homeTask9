
package by.itstep.fabiyanski.stock;

import by.itstep.fabiyanski.stock.Exceptions.GoodsItemAmountIsNotEnoughException;
import by.itstep.fabiyanski.stock.Exceptions.GoodsItemDoesntExistsException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TheStockService implements StockService {

    private Set<String> provisionersList;//список внешних поставщиков

    public TheStockService() {
        this.provisionersList = new HashSet<>();
    }

    public void takeTheGoodsFromProvisioner(Stock stock, Waybill waybill) { // если один и тот же товар приезжает по той же цене, то количество
        // этого товара увеличивается в первоначальной строке списка товаров; если же один и тот же товар приезжает по другой цене,
        // то он добавляется в список товаров отдельной строкой
        ArrayList<GoodsItem> addedList = waybill.getGoodsItems();
        List<GoodsItem> stockList = stock.getStockGoodsItems();
        ArrayList<GoodsItem> newList = new ArrayList<>();
        if (stockList.isEmpty()) stock.setStockGoodsItems(waybill.getGoodsItems());
        else {
            for (GoodsItem item : addedList) {
                boolean theSameGood = false;
                for (GoodsItem element : stockList) {
                    if (item.getNameOfProduct().equals(element.getNameOfProduct()) && item.getPrice().equals(element.getPrice())) {
                        element.setAmount(element.getAmount() + item.getAmount());
                        element.setCost(element.getAmount() * element.getPrice());
                        theSameGood = true;
                    } else if (!theSameGood) {
                        newList.add(item);
                        theSameGood = true;
                    }
                    newList.add(element);
                }
            }
            stock.setStockGoodsItems(newList);
        }
        provisionersList.add(waybill.getProvisioner());
    }

    public void takeTheGoodsFromStockToStock(Stock stockFrom, Stock stockTo, Waybill waybill) throws GoodsItemDoesntExistsException,
            GoodsItemAmountIsNotEnoughException {
        ArrayList<GoodsItem> neededList = waybill.getGoodsItems();
        List<GoodsItem> stockFromList = stockFrom.getStockGoodsItems();
        for (GoodsItem item : neededList) {
            double neededAmount = item.getAmount();
            double balance = 0.00;
            int count = 0;
            for (GoodsItem element : stockFromList) {
                if (item.getNameOfProduct() == element.getNameOfProduct()) {
                    count++;
                    balance = element.getAmount() - neededAmount;
                    neededAmount = neededAmount - element.getAmount();
                    if (balance >= 0) {
                        element.setAmount(balance);
                        element.setCost(element.getPrice() * balance);
                    } else {
                        element.setAmount(0.00);
                        element.setCost(0.00);
                    }
                }
            }
            if (count == 0) throw new GoodsItemDoesntExistsException();
            if (balance < 0) throw new GoodsItemAmountIsNotEnoughException();
        }
        takeTheGoodsFromProvisioner(stockTo, waybill);
        List<GoodsItem> newStockFromList = stockFromList.stream().//удаляем элементы из списка, количество которых равно 0
                filter(item -> item.getAmount() != 0.00).collect(Collectors.toList());
        stockFrom.setStockGoodsItems(newStockFromList);
    }

    public void printListOfAllGoods(Stock stock) {
        stock.getStockGoodsItems().forEach(item -> System.out.println(item));
    }


    public void printListOfAllProvisioners() {
        System.out.println(provisionersList);
    }

    public List<GoodsItem> search(Stock stock, String name) throws GoodsItemDoesntExistsException {
        List<GoodsItem> searchedList =
                stock.getStockGoodsItems().stream().
                        filter(item -> item.getNameOfProduct() == name).
                        collect(Collectors.toList());
        if (searchedList.isEmpty()) throw new GoodsItemDoesntExistsException();
        return searchedList;
    }
}