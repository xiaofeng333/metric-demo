package com.custom.feng.metricdemo.metricType;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HistogramTest {

    /**
     * Histogram统计数据的分布情况, 比如最小值, 最大值, 中间值, 还有中位数, 75百分位, 90百分位, 95百分位, 98百分位, 99百分位, 和 99.9百分位的值
     */
    public static void main(String[] args) {
        MetricRegistry metricRegistry = new MetricRegistry();
        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry).build();
        consoleReporter.start(1, TimeUnit.MILLISECONDS);
        Histogram histogram = metricRegistry.histogram(MetricRegistry.name(HistogramTest.class, "queue", "histogram"));
        int i = 0;
        Random random = new Random();
        while (i < 1000) {
            histogram.update(random.nextInt(10000));
            i++;
        }

    }
}
