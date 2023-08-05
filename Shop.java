import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Shop {

static Random random = new Random();
static void generateEmployee(List<Toys> toy){ // Метод рандомной генерации игрушек.

    int count = 10;
    Random rand = new Random();

    for (int i = 1; i < count; i++) {
        String[] name = new String[] { "Тигр", "Медведь", "Машинка", "Вертолет", "Кораблик", "Уточка", "Мышка", "Кукла", "Кошка", "Собака" };
        int id = i;

        if (rand.nextInt(5) == 0) {
            toy.add(new Toy(id, name[random.nextInt(10)]));
        }
        else {
            toy.add(new Toy(id, name[random.nextInt(10)]));
        }
        //System.out.println(toy.get(i)); Вывод списка игрушек в консоль
    }
}
    public static void main(String[] args) throws IOException{
        List<Toys> toy = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        
        FileCreate(); //Вызов метода создания файла
        generateEmployee(toy); //Вызов метода генерации списка игрушек

        System.out.println("---Выберите функцию (введите номер)---");
        String menu = "1. Добавить игрушки в список" + "\n" + "2. Разыграть игрушку" + "\n" + "3. Выход из программы";
        
        System.out.println(menu);
        
        boolean isQuit = false;
        while (!isQuit) {
            byte functuion = sc.nextByte();
            
            switch (functuion){
                case 1:
                try {
                    FileWriter writer = new FileWriter("Игрушки.txt");
                    for (int i = 0; i < toy.size(); i++){
                        writer.write(toy.get(i) + "\n"); // Запись списка игрушек в файл
                    }
                    System.out.println("Запись успешна");
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Ошибка при записи в файл");
                    e.printStackTrace();
                }
                break;
                
                case 2:
                    RandomDrop(); // Вызов метода рандомного выбора игрушки из списка.
                    break;

                case 3:
                    isQuit = true;
                    System.out.println("Приложение завершает работу");
                    break;

                default:
                    System.out.println("No such menu");
                    break;
        }
    }
}

private static void FileCreate() throws IOException { // Метод создания файла с игрушками.
    File file = new File("Игрушки.txt");
        if (file.createNewFile()){
            System.out.println("Файл со списком игрушек создан");
        } else {
            System.out.println("Ошибка создания файла, файл существует!");
        }
    }

private static void RandomDrop() { // Метод розыгрыша игрушки под средством выбора через рандом.
    List<String> lines = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader("Игрушки.txt"))) {
        
        String line;

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        if (!lines.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(lines.size());
            String selectedLine = lines.get(randomIndex);
                    
            lines.remove(randomIndex); // Удаление строки из файла с игрушками.

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Игрушки.txt"))) {
            for (String removeLine : lines) {
                writer.write(removeLine + System.getProperty("line.separator"));
            }
        }

        writeLineToFile("Розыгрыш.txt", selectedLine);
    
        System.out.println("Поздравляем, вы выиграли игрушку" + " - " + selectedLine);
        } else {
            System.out.println("Файл с игрушками пуст, пожалуйста перезаполните список.");
        }
    } catch (IOException e) {
        System.out.println("Ошибка при чтении файла: " + e.getMessage());
    }
}
    
private static void writeLineToFile(String filePath, String line) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        writer.write(line);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}