import org.example.Main;
import org.example.repository.EmployeeRepository;
import org.example.util.EmployeeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = Main.class)
public class DataBaseTest {

    @Autowired
    EmployeeRepository repo;

    @Test
    void insertEmployees() throws InterruptedException {
        long recordsNum = 50_000_000;
        int threadsNum = 10;
        long recordsForThreadNum = recordsNum / threadsNum;

        ExecutorService exec = Executors.newFixedThreadPool(threadsNum);

        Runnable task = () -> {
            for (int i = 0; i < recordsForThreadNum; i++) {
                repo.save(EmployeeUtil.nextFakeEmployee());
            }
        };

        long start = System.nanoTime();

        for (int i = 0; i < threadsNum; i++) {
            exec.submit(task);
        }

        exec.shutdown();

        while (!exec.awaitTermination(30, TimeUnit.MINUTES)) {

        }

        long stop = System.nanoTime();
        System.out.println("Время выполнения " + ((float) (stop - start)) / 1000_000_000 + "с");
    }
}
