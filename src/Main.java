import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        log.info("Приложение запущено");

        System.out.print("Введите скидку в % (0 - без скидки): ");
        double discount = Double.parseDouble(scanner.nextLine());
        Receipt receipt = new Receipt(discount);
        log.info("Создан чек со скидкой {}%", discount);

        System.out.println("Вводите товары. Пустое название = завершить.");

        while (true) {
            System.out.print("Название товара: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                log.info("Ввод товаров завершён");
                break;
            }

            System.out.print("Количество: ");
            int qty = Integer.parseInt(scanner.nextLine());

            System.out.print("Цена: ");
            double price = Double.parseDouble(scanner.nextLine());

            Item item = new Item(name, qty, price);
            receipt.addItem(item);
            log.info("Добавлен товар: {}, кол-во: {}, цена: {}", name, qty, price);
        }

        printReceipt(receipt);

        scanner.close();
        log.info("Приложение завершено");
    }

    private static void printReceipt(Receipt receipt) {
        System.out.println("\n========== ЧЕК ==========");

        for (Item item : receipt.getItems()) {
            System.out.printf("%-20s %d x %.2f = %.2f руб.%n",
                    item.getName(), item.getQuantity(),
                    item.getPrice(), item.getTotal());
        }

        System.out.printf("Подытог: %.2f руб.%n", receipt.getSubtotal());

        if (receipt.getDiscountPercent() > 0) {
            System.out.printf("Скидка (%.0f%%): %.2f руб.%n",
                    receipt.getDiscountPercent(), receipt.getDiscount());
        }

        System.out.printf("ИТОГО: %.2f руб.%n", receipt.getTotal());
    }
}