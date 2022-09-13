import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        try (Scanner s = new Scanner(System.in)) {
            WorkTask workTask = new WorkTask(s.nextInt());
            workTask.calculate();
        }
    }
}
