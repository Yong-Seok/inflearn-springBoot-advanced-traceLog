package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

    @Test
    void strategyV2() {
        // 익명 내부 클래스 변수 사용
        Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("전략 패턴(익명 내부클래스 변수 선언) 실행 ");
            }
        };
        ContextV1 context1 = new ContextV1(strategyLogic1);
        log.info("strategyLogic1={}", strategyLogic1.getClass());
        context1.execute();
    }

    @Test
    void strategyV3() {
        // 익명 내부 클래스 생성자에 바로 주입 사용
        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("전략 패턴(익명 내부클래스 생성자) 로직 실행 ");
            }
        });
        context1.execute();
    }

    @Test
    void strategyV4() {
        // 람다 사용. 인터페이스에 메서드가 1개만 있어야 한다.
        ContextV1 context1 = new ContextV1(() -> log.info("전략 패턴(익명 내부클래스 람다) 로직 실행 "));
        context1.execute();
    }
}
