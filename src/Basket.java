import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket implements Serializable {
    private static List<String> goods;
    private static List<Integer> price;
    private static final Map<Integer, Integer> allGoods = new HashMap<>();

    public Basket(List<String> goods, List<Integer> price) {
        Basket.goods = goods;
        Basket.price = price;
    }

    static List<String> getGoods() {
        return goods;
    }

    static List<Integer> getPrice() {
        return price;
    }

    public static Map<Integer, Integer> getAllGoods() {
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

    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (int i = 0; i < getGoods().size(); i++) {
                if (getAllGoods().get(i) != null) {
                    String tmp = getGoods().get(i) + ": " + getAllGoods().get(i) + " ед. " + " по цене " + getPrice().get(i) + " руб./ед. ";
                    out.print(tmp);
                    out.print('\n');
                }
            }
        }
        System.out.println("Информация успешно сохранена");
    }

    public static Basket loadFromTxtFile(File textFile) {
        File file = new File(textFile.toURI());
        try (FileReader fileReader = new FileReader(file);) {
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            int i = 0;
            while (line != null) {
                System.out.println(line);
                String[] parse = line.split(" ");
                allGoods.put(i, Integer.valueOf(parse[1]));
                i++;
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Basket() {
    }

    public void saveBin(File file) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));//("basket.bin")
        //Basket outBin = new Basket():
        out.writeObject(new Basket());
        out.close();
    }

    public static void loadFromBinFile(File file) throws IOException, ClassNotFoundException {
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

