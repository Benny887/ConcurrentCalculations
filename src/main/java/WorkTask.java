import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WorkTask {

    private final double countOfOperations;
    private final int partOfWork;
    private static final int COUNT_OF_THREADS = Runtime.getRuntime().availableProcessors();
    private final ExecutorService service = Executors.newFixedThreadPool(WorkTask.COUNT_OF_THREADS);

    private static final Logger log = Logger.getLogger(WorkTask.class);


    public WorkTask(double countOfOperations) {
        this.countOfOperations = countOfOperations;
        log.info("Начало работы программы.");
        partOfWork = (int) Math.ceil(this.countOfOperations / COUNT_OF_THREADS);
    }


    public double calculate() throws ExecutionException, InterruptedException {
        double result = 0;
        List<Task> threads = new ArrayList<>();
        List<Future<Double>> resultParts = new ArrayList<>();
        log.info("Размер пула потоков составляет " + COUNT_OF_THREADS);
        for (int i = 0, end; i < countOfOperations; i = end) {
            end = i + partOfWork;
            Task task = new Task(i, end < countOfOperations ? end : (int) countOfOperations);
            threads.add(task);
            resultParts.add(service.submit(task));
        }
        for (int i = 0; i < threads.size(); i++) {
            result += resultParts.get(i).get();
        }
        log.info("Результат работы потоков равен " + result);
        service.shutdown();
        log.info("Пул потоков закрыт.Программа завершена.");
        return result;
    }

}