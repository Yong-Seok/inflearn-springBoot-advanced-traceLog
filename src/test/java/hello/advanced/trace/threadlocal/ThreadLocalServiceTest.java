package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> {
            service.logic("userA");
        };

        Runnable userB = () -> {
            service.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("Thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("Thread-B");

        threadA.start();
        sleep(200);
        threadB.start();

        // 메인 쓰레드 종료 대기
        sleep(3000);

    }

    private void sleep(int millis) {
        try {
            Thread.sleep((millis));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
