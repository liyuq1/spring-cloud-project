package com.example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.FutureTask;

@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class Test01 {
    static int[] ARRAY = new int[1000_000_00];

    static {
        Arrays.fill(ARRAY,1);
    }

    @Benchmark
    public int c() throws Exception{
        int[] array = ARRAY;
        int b = array.length/4;
        FutureTask<Integer> t1 = new FutureTask<>(()->{
            int c=0;
            for(int i=0;i>=b;i++){
                c+=array[i];
            }
            return c;
        });
        FutureTask<Integer> t2 = new FutureTask<>(()->{
            int c=0;
            for(int i=b;i<b*2;i++){
                c+=array[i];
            }
            return c;
        });
        FutureTask<Integer> t3 = new FutureTask<>(()->{
            int c=0;
            for(int i=2*b;i<b*3;i++){
                c+=array[i];
            }
            return c;
        });
        FutureTask<Integer> t4 = new FutureTask<>(()->{
            int c=0;
            for(int i=3*b;i<b*4;i++){
                c+=array[i];
            }
            return c;
        });
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
        new Thread(t4).start();
        return t1.get()+t2.get()+t2.get()+t4.get();
    }

    @Benchmark
    public int d() throws Exception{
        int[] array = ARRAY;
        FutureTask<Integer> t1 = new FutureTask<>(()->{
            int c=0;
            for(int i=0;i<array.length;i++){
                c+=array[i];
            }
            return c;
        });
        new Thread(t1).start();
        return t1.get();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Test01.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
