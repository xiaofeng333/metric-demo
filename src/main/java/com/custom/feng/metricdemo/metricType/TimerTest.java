package com.custom.feng.metricdemo.metricType;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TimerTest {

    /**
     * Timer其实是Histogram和Meter的结合, histogram某部分代码/调用的耗时, meter统计TPS
     */
    public static void main(String[] args) throws InterruptedException {
        MetricRegistry metricRegistry = new MetricRegistry();
        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry).build();
        consoleReporter.start(1, TimeUnit.SECONDS);
        Timer timer = metricRegistry.timer(MetricRegistry.name(TimerTest.class, "queue", "timer"));
        Timer.Context context;
        Random random = new Random();
        int i = 0;
        while (i < 100) {
            context = timer.time();
            Thread.sleep(random.nextInt(1000));
            context.stop();
            i++;
        }
    }
}
