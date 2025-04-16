import lombok.extern.slf4j.Slf4j;
import org.example.Main;
import org.example.repository.EmployeeRepository;
import org.example.util.EmployeeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@SpringBootTest(classes = Main.class)
@Slf4j
public class DataBaseTest {
    long recordsNum = 50_000_000;
    int threadsNum = 10;
    long recordsForThreadNum = recordsNum / threadsNum;
    ExecutorService threadsPool = Executors.newFixedThreadPool(threadsNum);

    @Autowired
    EmployeeRepository repo;

    @Test
    void insertEmployees() {
        long start = System.nanoTime();

        CompletableFuture<?>[] futures = IntStream.range(0, threadsNum)
                .mapToObj(next -> CompletableFuture.runAsync(() -> {
                    log.info("Задача №{} начала работу", next);

                    for (int i = 0; i < recordsForThreadNum; i++) {
                        repo.save(EmployeeUtil.nextFakeEmployee());
                    }
                    log.info("Задача №{} завершена", next);
                }, threadsPool))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();

        long stop = System.nanoTime();
        log.info("Время выполнения всех задач {} с", ((float) (stop - start)) / 1000_000_000);
    }
}
