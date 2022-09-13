import org.apache.log4j.Logger;

import java.util.concurrent.Callable;

public class Task implements Callable<Double> {

    private int startValue;
    private final int endValue;

    private static final Logger log = Logger.getLogger(Task.class);

    public Task(int startValue, int endValue) {
        this.startValue = startValue;
        this.endValue = endValue;
    }

    @Override
    public Double call() {
        log.info("Поток начал выполнение задачи.");
        double locSum = 0;
        while (startValue < endValue) {
            locSum += 1 / Math.pow(2, startValue);
            startValue++;
        }
        log.info("Поток окончил выполнение задачи.");
        return locSum;
    }
}
