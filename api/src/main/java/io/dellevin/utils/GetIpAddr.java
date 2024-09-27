package io.dellevin.utils;

import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import org.lionsoul.ip2region.xdb.Searcher;

public class GetIpAddr {
    public static String getIpAddr(String ip) {
        // 1、创建 searcher 对象
        String dbPath = "";
        // 1、从 dbPath 中预先加载 VectorIndex 缓存
        try (InputStream inputStream = GetIpAddr.class.getClassLoader().getResourceAsStream("ip2region/ip2region.xdb")) {
            if (inputStream == null) {
                throw new FileNotFoundException("Resource not found: ip2region/ip2region.xdb");
            }

            // 创建临时文件
            File tempFile = Files.createTempFile("ip2region", ".xdb").toFile();
            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            // 获取临时文件路径
            dbPath = tempFile.getAbsolutePath();
        } catch (IOException e) {
            System.out.printf("failed to load vector index: %s\n", e);
            return "failed to load vector index: " + e + " ";
        }

        // 1、从 dbPath 中预先加载 VectorIndex 缓存，并且把这个得到的数据作为全局变量，后续反复使用。
        byte[] vIndex;
        try {
            vIndex = Searcher.loadVectorIndexFromFile(dbPath);
        } catch (Exception e) {
            System.out.printf("failed to load vector index from `%s`: %s\n", dbPath, e);
            return "failed to load vector index from "+dbPath+": "+e+" ";
        }

        // 2、使用全局的 vIndex 创建带 VectorIndex 缓存的查询对象。
        Searcher searcher = null;
        try {
            searcher = Searcher.newWithVectorIndex(dbPath, vIndex);
        } catch (Exception e) {
            System.out.printf("failed to create vectorIndex cached searcher with `%s`: %s\n", dbPath, e);
            return "failed to create vectorIndex cached searcher with "+dbPath+": "+e+" ";
        }
        String region ="";
        // 3、查询
        try {
//            long sTime = System.nanoTime();
            region = searcher.search(ip);
//            long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));
//            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
        } catch (Exception e) {
            System.out.printf("failed to search(%s): %s\n", ip, e);
            return "failed to search("+ip+"): "+e+" ";
        }

        // 4、关闭资源
        try {
            searcher.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return region;
    }
}
