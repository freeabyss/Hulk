package science.freeabyss.hulk.demo.spring.springido1;

import java.io.*;

/**
 * Created by abyss on 3/25/16.
 */
public class PerformanceException extends Exception {
    public static void main(String[] args) {
//        System.out.println(fuck(1034));
        String path = "/Users/abyss/Downloads/gradle-2.11-all.zip";
        long a = System.currentTimeMillis();
        buffer(path);
        long b = System.currentTimeMillis();
        file(path);
        long c= System.currentTimeMillis();

        System.out.println("buffer :" + (b - a));
        System.out.println("file : " + (c - b));
    }

    public static void file(String path) {
        try (FileInputStream inputStream = new FileInputStream(path); ) {
            int c ;
            while ((c = inputStream.read()) != -1) {
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void buffer(String path) {
        try (FileInputStream inputStream = new FileInputStream(path);
             BufferedInputStream buffer = new BufferedInputStream(inputStream);) {
            int c ;
            while ((c = buffer.read()) != -1) {
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int fuck(int i) {
        String s = String.valueOf(i);
        return Integer.parseInt(s.replaceAll("0", "") + s.replaceAll("[1-9]", ""));
//        FilterInputStream
//        System.arraycopy();

    }

}
