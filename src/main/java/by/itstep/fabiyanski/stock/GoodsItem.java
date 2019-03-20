package by.itstep.fabiyanski.stock;


public class GoodsItem {// товарная позиция
    private String nameOfProduct;//наименование
    private String measure;//единица измерения
    private Double amount;//количество
    private Double price;//цена
    private Double cost;//стоимость

    public GoodsItem(String nameOfProduct, String measure, Double amount, Double price) {
        this.nameOfProduct = nameOfProduct;
        this.measure = measure;
        this.amount = amount;
        this.price = price;
        this.cost = amount * price;
    }

    @Override
    public String toString() {
        return "{" +
                "nameOfProduct='" + nameOfProduct + '\'' +
                ", measure='" + measure + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", cost=" + cost +
                '}';
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getPrice() {
        return price;
    }
}
