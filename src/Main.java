import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Basket cart = new Basket(List.of("Хлеб", "Масло", "Молоко", "Яйца"), List.of(45, 130, 100, 120));
        System.out.println("Список доступных товаров: ");
        for (int i = 0; i < Basket.getGoods().size(); i++) {
            System.out.println((i + 1) + ". " + Basket.getGoods().get(i) + " по цене: " + Basket.getPrice().get(i) + " руб./ед.");
        }

        Scanner scanner = new Scanner(System.in);
        int costCart = 0;

        while (true) {
            System.out.println("Введите номер товара и его количество.Для просмотра корзины введите load.");
            System.out.println("По заврешении заполнения корзины и расчета стоимости покупок нажмите end.");
            String input = scanner.nextLine();

            if ("end".equals(input)) {
                for (int i = 0; i < Basket.getAllGoods().size(); i++) {
                    if (Basket.getAllGoods().get(i) == null) {
                        costCart = Basket.getAllGoods().get(i) * Basket.getPrice().get(i);
                    } else {
                        costCart += Basket.getAllGoods().get(i) * Basket.getPrice().get(i);
                    }
                }
                cart.printCart();
                System.out.println("Итого товароов  на сумму: " + costCart + " руб.");
                break;
            }
            if ("load".equals(input)) {
                Basket.loadFromBinFile(new File("basket.bin"));
                System.out.println("Информация загружена");
                continue;
            }
            String[] parse = input.split(" ");
            if (parse.length != 2) {
                System.out.println("Необходимо ввести 2 значения: номер товара и его количество. Вы ввели: " + parse.length);
                continue;
            } else {
                try {
                    int numProduct = Integer.parseInt(parse[0]) - 1;
                    int amount = Integer.parseInt(parse[1]);
                    cart.addToCart(numProduct, amount);
                    cart.saveBin(new File("basket.bin"));
                } catch (NumberFormatException e) {
                    System.out.println("Введены некорректные данные: необходимо ввести числовые значения");
                    continue;
                }
            }
        }
    }
}
