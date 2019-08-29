package com.custom.feng.metricdemo.metricType;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class CounterTest {
    private static Queue<String> q = new LinkedList<>();

    /**
     * Counter是计数器, 只是用Gauge封装了AtomicLong
     */
    public static void main(String[] args) throws InterruptedException {
        MetricRegistry metricRegistry = new MetricRegistry();
        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry).build();
        consoleReporter.start(1, TimeUnit.SECONDS);
        Counter counter = metricRegistry.counter(MetricRegistry.name(CounterTest.class, "queue", "counter"));
        int num = 1;
        while (q.size() < 30) {
            Thread.sleep(200);
            if (Math.random() > 0.7) {
                String poll = q.poll();
                System.out.println("take job" + poll);
                counter.dec();
            } else {
                System.out.println("add job" + num);
                q.add("job" + num++);
                counter.inc();
            }
        }

    }
}
