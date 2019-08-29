package com.custom.feng.metricdemo.metricType;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

public class MeterTest {

    /**
     * Meter度量一系列事件发生的速率(rate), 例如TPS。Meters会统计最近1分钟, 5分钟, 15分钟的速率
     */
    public static void main(String[] args) throws InterruptedException {
        MetricRegistry metricRegistry = new MetricRegistry();
        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry).build();
        consoleReporter.start(1, TimeUnit.MINUTES);
        Meter meter = metricRegistry.meter(MetricRegistry.name(MetricRegistry.class, "queue", "meter"));
        for (int i = 100; i <= 200; i = i + 100) {
            int j = i;
            while (j > 0) {
                meter.mark();
                j--;
            }
            Thread.sleep(60000);
        }
    }
}
