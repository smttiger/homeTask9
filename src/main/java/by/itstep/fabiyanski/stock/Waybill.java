package by.itstep.fabiyanski.stock;

import java.util.ArrayList;

public class Waybill { //накладная

    private String date;//дата
    private String provisioner;//поставщик
    private String consignee;//получатель
    private String contract;//договор(основание отгрузки)
    private String proxyNumber;//номер доверенности
    private String proxyDate;//дата доверенности
    private String personWhoShippedTheGoods;//отгрузил
    private String personWhoAcceptedTheGoods;//принял
    private ArrayList<GoodsItem> goodsItems;

    public Waybill(String date, String provisioner, String consignee, String contract,
                   String proxyNumber, String proxyDate, String personWhoShippedTheGoods,
                   String personWhoAcceptedTheGoods, ArrayList<GoodsItem> goodsItems) {
        this.date = date;
        this.provisioner = provisioner;
        this.consignee = consignee;
        this.contract = contract;
        this.proxyNumber = proxyNumber;
        this.proxyDate = proxyDate;
        this.personWhoShippedTheGoods = personWhoShippedTheGoods;
        this.personWhoAcceptedTheGoods = personWhoAcceptedTheGoods;
        this.goodsItems = goodsItems;
    }

    public ArrayList<GoodsItem> getGoodsItems() {
        return goodsItems;
    }

    public String getProvisioner() {
        return provisioner;
    }
}
