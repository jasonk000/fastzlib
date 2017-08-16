package com.bluedevel.zlib;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.zip.DeflaterOutputStream;

import org.apache.commons.io.FileUtils;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@Fork(1)
@Warmup(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class BenchmarkCompressors {
    private final byte[] COMPRESS_TEXT;

    public BenchmarkCompressors() {
        try {
            COMPRESS_TEXT = FileUtils.readFileToString(new File("./src/jmh/java/com/bluedevel/zlib/book1")).getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    public void compressJvm(Blackhole bh) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        DeflaterOutputStream output = new DeflaterOutputStream(baos);
        output.write(COMPRESS_TEXT);
        output.flush();
        output.close();
        bh.consume(baos);
    }

    @Benchmark
    public void compressFastJvm(Blackhole bh) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        FastDeflaterOutputStream output = new FastDeflaterOutputStream(baos);
        output.write(COMPRESS_TEXT);
        output.flush();
        output.close();
        bh.consume(baos);
    }
}
