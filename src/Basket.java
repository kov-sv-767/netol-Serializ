import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket implements Serializable {
    private  List<String> goods;
    private List<Integer> price;
    private final Map<Integer, Integer> allGoods = new HashMap<>();

    public Basket(List<String> goods, List<Integer> price) {
        this.goods = goods;
        this.price = price;
    }

     List<String> getGoods() {
        return goods;
    }

    List<Integer> getPrice() {
        return price;
    }

    Map<Integer, Integer> getAllGoods() {
        return allGoods;
    }

    public void addToCart(int numProduct, int amount) {
        if (allGoods.containsKey(numProduct)) {
            allGoods.put(numProduct, allGoods.get(numProduct) + amount);
        } else {
            allGoods.put(numProduct, amount);
        }
    }

    public void printCart() {
        for (int i = 0; i < getGoods().size(); i++) {
            if (getAllGoods().get(i) != null) {
                System.out.println((i + 1) + ". " + getGoods().get(i) + ": " + (getGoods().get(i))
                        + " ед. " + " x " + getPrice().get(i) + " руб/ед. " + " = "
                        + getAllGoods().get(i) * getPrice().get(i) + " руб. ");
            }
        }
    }

    public Basket() {
    }

    public void saveBin(File file) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));//("basket.bin")
        out.writeObject(new Basket());
        out.close();
    }

    public void loadFromBinFile(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Basket inBin = (Basket) in.readObject();
        for (int i = 0; i < getGoods().size(); i++) {
            if (getAllGoods().get(i) != null) {
                System.out.println(getGoods().get(i) + ": " + getAllGoods().get(i)
                        + " ед. " + " по цене " + getPrice().get(i) + " руб./ед. ");
            }
        }
        in.close();
    }


}

