import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Basket cart = new Basket(List.of("Хлеб", "Масло", "Молоко", "Яйца"), List.of(45, 130, 100, 120));
        System.out.println("Список доступных товаров: ");
        for (int i = 0; i < cart.getGoods().size(); i++) {
            System.out.println((i + 1) + ". " + cart.getGoods().get(i) + " по цене: " + cart.getPrice().get(i) + " руб./ед.");
        }

        Scanner scanner = new Scanner(System.in);
        int costCart = 0;

        while (true) {
            System.out.println("Введите номер товара и его количество.Для просмотра корзины введите load.");
            System.out.println("По заврешении заполнения корзины и расчета стоимости покупок нажмите end.");
            String input = scanner.nextLine();

            if ("end".equals(input)) {
                for (int i = 0; i < cart.getAllGoods().size(); i++) {
                    if (cart.getAllGoods().get(i) == null) {
                        costCart = cart.getAllGoods().get(i) * cart.getPrice().get(i);
                    } else {
                        costCart += cart.getAllGoods().get(i) * cart.getPrice().get(i);
                    }
                }
                cart.printCart();
                System.out.println("Итого товароов  на сумму: " + costCart + " руб.");
                break;
            }
            if ("load".equals(input)) {
                cart.loadFromTxtFile(new File("cart.txt"));
                System.out.println("Информация загружена");
                continue;
            }
            String[] parse = input.split(" ");
            if (parse.length != 2) {
                System.out.println("Необходимо ввести 2 значения: номер товара и его количество. Вы ввели: " + parse.length);
            } else {
                try {
                    int numProduct = Integer.parseInt(parse[0]) - 1;
                    int amount = Integer.parseInt(parse[1]);
                    cart.addToCart(numProduct, amount);
                    cart.saveTxt(new File("cart.txt"));
                } catch (NumberFormatException e) {
                    System.out.println("Введены некорректные данные: необходимо ввести числовые значения");
                }
            }
        }
    }
}
