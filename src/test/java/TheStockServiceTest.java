import by.itstep.fabiyanski.stock.Exceptions.GoodsItemAmountIsNotEnoughException;
import by.itstep.fabiyanski.stock.Exceptions.GoodsItemDoesntExistsException;
import by.itstep.fabiyanski.stock.GoodsItem;
import by.itstep.fabiyanski.stock.Stock;
import by.itstep.fabiyanski.stock.TheStockService;
import by.itstep.fabiyanski.stock.Waybill;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TheStockServiceTest {
    @Test
    public void takeTheGoodsFromProvisionerTest() {
        TheStockService stockService = new TheStockService();
        Assert.assertNotNull(stockService);
        Stock stock1 = new Stock("Broadway 1");
        Assert.assertNotNull(stock1);
        ArrayList<GoodsItem> list1 = new ArrayList<>();
        GoodsItem item1 = new GoodsItem("Клей", "Шт.", 42.00, 10.00);
        GoodsItem item2 = new GoodsItem("Дюбель-зонтик", "Шт.", 350.00, 0.19);
        list1.add(item1);
        list1.add(item2);
        Waybill waybill1 = new Waybill("01.01.2019", "Диском", "XXX", "17/18ГР",
                "123", "20/11/2018", "Иванов", "Петров", list1);
        Assert.assertNotNull(waybill1);
        stockService.takeTheGoodsFromProvisioner(stock1, waybill1);
        System.out.println("После добавления товаров на пустой склад: ");
        stock1.getStockGoodsItems().forEach(System.out::println);
        System.out.println();

        ArrayList<GoodsItem> list2 = new ArrayList<>();
        GoodsItem item3 = new GoodsItem("Клей", "Шт.", 10.00, 10.00);
        list2.add(item3);
        Waybill waybill2 = new Waybill("03.01.2019", "Диском", "XXX", "17/18ГР",
                "123", "20/11/2018", "Иванов", "Петров", list2);
        stockService.takeTheGoodsFromProvisioner(stock1, waybill2);
        System.out.println("После добавления 10-ти мешков клея по той же цене:");
        stock1.getStockGoodsItems().forEach(System.out::println);

        ArrayList<GoodsItem> list3 = new ArrayList<>();
        GoodsItem item4 = new GoodsItem("Клей", "Шт.", 10.00, 15.00);
        list3.add(item4);
        Waybill waybill3 = new Waybill("03.01.2019", "Диском", "XXX", "17/18ГР",
                "123", "20/11/2018", "Иванов", "Петров", list3);
        stockService.takeTheGoodsFromProvisioner(stock1, waybill3);
        System.out.println();
        System.out.println("После добавления 10-ти мешков клея по другой цене:");
        stock1.getStockGoodsItems().forEach(System.out::println);
    }

    @Test
    public void takeTheGoodsFromStockToStockTest() throws GoodsItemDoesntExistsException,
            GoodsItemAmountIsNotEnoughException {
        TheStockService stockService = new TheStockService();
        Stock stock1 = new Stock("Понемуньская 1");
        Stock stock2 = new Stock("Горького 1");
        ArrayList<GoodsItem> list1 = new ArrayList<>();
        GoodsItem item1 = new GoodsItem("Клей", "Шт.", 42.00, 10.00);
        GoodsItem item2 = new GoodsItem("Дюбель-зонтик", "Шт.", 350.00, 0.19);
        list1.add(item1);
        list1.add(item2);
        Waybill waybill1 = new Waybill("01.01.2019", "Диском", "XXX", "17/18ГР",
                "123", "20/11/2018", "Иванов", "Петров", list1);
        stockService.takeTheGoodsFromProvisioner(stock1, waybill1);
        System.out.println("Первоначальный список товаров на складе 1:");
        stock1.getStockGoodsItems().forEach(System.out::println);
        ArrayList<GoodsItem> list2 = new ArrayList<>();
        GoodsItem item3 = new GoodsItem("Клей", "Шт.", 22.00, 10.00);
        GoodsItem item4 = new GoodsItem("Дюбель-зонтик", "Шт.", 50.00, 0.19);
        list2.add(item3);
        list2.add(item4);
        Waybill waybill2 = new Waybill("01.01.2019", "XXX", "XXX", "17/18ГР",
                "123", "20/11/2018", "Иванов", "Петров", list2);
        stockService.takeTheGoodsFromStockToStock(stock1, stock2, waybill2);
        System.out.println();
        System.out.println("Остаток товаров на складе 1 после перемещения :");
        stock1.getStockGoodsItems().forEach(System.out::println);
        System.out.println();
        System.out.println("Список товаров на складе 2 после перемещения:");
        stock2.getStockGoodsItems().forEach(System.out::println);

        ArrayList<GoodsItem> list3 = new ArrayList<>();
        GoodsItem item5 = new GoodsItem("Клей", "Шт.", 20.00, 10.00);
        list3.add(item5);
        Waybill waybill3 = new Waybill("01.01.2019", "XXX", "XXX", "17/18ГР",
                "123", "20/11/2018", "Иванов", "Петров", list3);
        stockService.takeTheGoodsFromStockToStock(stock1, stock2, waybill3);
        System.out.println();
        System.out.println("Список товаров на складе 1 после перемещения 20-ти мешков клея из него на другой склад:");
        stock1.getStockGoodsItems().forEach(System.out::println);
    }

    @Test(expected = GoodsItemDoesntExistsException.class)
    public void takeTheGoodsFromStockToStockInvalidGoodsItemTest() throws GoodsItemDoesntExistsException,
            GoodsItemAmountIsNotEnoughException {
        TheStockService stockService = new TheStockService();
        Stock stock1 = new Stock("Понемуньская 1");
        Stock stock2 = new Stock("Горького 1");
        ArrayList<GoodsItem> list1 = new ArrayList<>();
        GoodsItem item1 = new GoodsItem("Клей", "Шт.", 42.00, 10.00);
        GoodsItem item2 = new GoodsItem("Дюбель-зонтик", "Шт.", 350.00, 0.19);
        list1.add(item1);
        list1.add(item2);
        Waybill waybill1 = new Waybill("01.01.2019", "Диском", "XXX", "17/18ГР",
                "123", "20/11/2018", "Иванов", "Петров", list1);
        stockService.takeTheGoodsFromProvisioner(stock1, waybill1);
        ArrayList<GoodsItem> list2 = new ArrayList<>();
        GoodsItem item3 = new GoodsItem("Краска", "Шт.", 22.00, 10.00);
        list2.add(item3);
        Waybill waybill2 = new Waybill("01.01.2019", "XXX", "XXX", "17/18ГР",
                "123", "20/11/2018", "Иванов", "Петров", list2);
        stockService.takeTheGoodsFromStockToStock(stock1, stock2, waybill2);
    }

    @Test(expected = GoodsItemAmountIsNotEnoughException.class)
    public void takeTheGoodsFromStockToStockInvalidAmountTest() throws GoodsItemDoesntExistsException,
            GoodsItemAmountIsNotEnoughException {
        TheStockService stockService = new TheStockService();
        Stock stock1 = new Stock("Понемуньская 1");
        Stock stock2 = new Stock("Горького 1");
        ArrayList<GoodsItem> list1 = new ArrayList<>();
        GoodsItem item1 = new GoodsItem("Клей", "Шт.", 42.00, 10.00);
        GoodsItem item2 = new GoodsItem("Дюбель-зонтик", "Шт.", 350.00, 0.19);
        list1.add(item1);
        list1.add(item2);
        Waybill waybill1 = new Waybill("01.01.2019", "Диском", "XXX", "17/18ГР",
                "123", "20/11/2018", "Иванов", "Петров", list1);
        stockService.takeTheGoodsFromProvisioner(stock1, waybill1);
        ArrayList<GoodsItem> list2 = new ArrayList<>();
        GoodsItem item3 = new GoodsItem("Клей", "Шт.", 50.00, 10.00);
        list2.add(item3);
        Waybill waybill2 = new Waybill("01.01.2019", "XXX", "XXX", "17/18ГР",
                "123", "20/11/2018", "Иванов", "Петров", list2);
        stockService.takeTheGoodsFromStockToStock(stock1, stock2, waybill2);
    }

    @Test
    public void printListOfAllGoodsTest() {
        TheStockService stockService = new TheStockService();
        Stock stock1 = new Stock("Broadway 1");
        ArrayList<GoodsItem> list1 = new ArrayList<>();
        GoodsItem item1 = new GoodsItem("Клей", "Шт.", 42.00, 10.00);
        GoodsItem item2 = new GoodsItem("Дюбель-зонтик", "Шт.", 350.00, 0.19);
        list1.add(item1);
        list1.add(item2);
        Waybill waybill1 = new Waybill("01.01.2019", "Диском", "XXX", "17/18ГР",
                "123", "20/11/2018", "Иванов", "Петров", list1);
        stockService.takeTheGoodsFromProvisioner(stock1, waybill1);
        stockService.printListOfAllGoods(stock1);
    }

    @Test
    public void printListOfAllProvisionersTest() {
        TheStockService stockService = new TheStockService();
        Stock stock1 = new Stock("Понемуньская 1");
        Stock stock2 = new Stock("Горького 1");
        ArrayList<GoodsItem> list1 = new ArrayList<>();
        GoodsItem item1 = new GoodsItem("Клей", "Шт.", 42.00, 10.00);
        GoodsItem item2 = new GoodsItem("Дюбель-зонтик", "Шт.", 350.00, 0.19);
        list1.add(item1);
        list1.add(item2);
        Waybill waybill1 = new Waybill("01.01.2019", "Диском", "XXX", "17/18ГР",
                "123", "20/11/2018", "Иванов", "Петров", list1);
        stockService.takeTheGoodsFromProvisioner(stock1, waybill1);
        ArrayList<GoodsItem> list2 = new ArrayList<>();
        GoodsItem item3 = new GoodsItem("Клей", "Шт.", 50.00, 10.00);
        list2.add(item3);
        Waybill waybill2 = new Waybill("01.01.2019", "Десятка", "XXX", "17/18ГР",
                "123", "20/11/2018", "Иванов", "Петров", list2);
        stockService.takeTheGoodsFromProvisioner(stock2, waybill2);
        stockService.printListOfAllProvisioners();
    }

    @Test
    public void searchPositiveTest() throws GoodsItemDoesntExistsException {
        TheStockService stockService = new TheStockService();
        Stock stock1 = new Stock("Понемуньская 1");
        ArrayList<GoodsItem> list1 = new ArrayList<>();
        GoodsItem item1 = new GoodsItem("Клей", "Шт.", 42.00, 10.00);
        GoodsItem item2 = new GoodsItem("Дюбель-зонтик", "Шт.", 350.00, 0.19);
        GoodsItem item3 = new GoodsItem("Клей", "Шт.", 20.00, 15.00);
        list1.add(item1);
        list1.add(item2);
        list1.add(item3);
        Waybill waybill1 = new Waybill("01.01.2019", "Диском", "XXX", "17/18ГР",
                "123", "20/11/2018", "Иванов", "Петров", list1);
        stockService.takeTheGoodsFromProvisioner(stock1, waybill1);
        stockService.search(stock1, "Клей").forEach(System.out::println);
    }

    @Test(expected = GoodsItemDoesntExistsException.class)
    public void searchNegativeTest() throws GoodsItemDoesntExistsException {
        TheStockService stockService = new TheStockService();
        Stock stock1 = new Stock("Понемуньская 1");
        ArrayList<GoodsItem> list1 = new ArrayList<>();
        GoodsItem item1 = new GoodsItem("Клей", "Шт.", 42.00, 10.00);
        list1.add(item1);
        Waybill waybill1 = new Waybill("01.01.2019", "Диском", "XXX", "17/18ГР",
                "123", "20/11/2018", "Иванов", "Петров", list1);
        stockService.takeTheGoodsFromProvisioner(stock1, waybill1);
        stockService.search(stock1, "Дюбель");
    }

}