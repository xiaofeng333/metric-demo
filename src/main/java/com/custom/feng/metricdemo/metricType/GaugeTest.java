package com.custom.feng.metricdemo.metricType;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class GaugeTest {
    private static Queue<String> q = new LinkedList<>();

    /**
     * gauge最简单的度量指标, 只有一个简单的返回值
     * 使用ConsoleReporter, 每隔一秒将度量指标输出到控制台
     */
    public static void main(String[] args) throws InterruptedException {
        MetricRegistry metricRegistry = new MetricRegistry();
        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry).build();
        consoleReporter.start(1, TimeUnit.SECONDS);
        metricRegistry.register(MetricRegistry.name(GaugeTest.class, "queue", "size"), (Gauge<Integer>) () -> q.size());
        do {
            Thread.sleep(1000);
            q.add(String.valueOf(System.currentTimeMillis()));
        } while (q.size() <= 100);
    }
}
