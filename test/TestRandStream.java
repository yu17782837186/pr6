package cn.io.com;

import java.io.*;

public class TestRandStream {
    public static void main(String[] args) throws IOException {
     File file = new File("src/cn/io/com/TestBufferReader.java");
     //文件的大小
     long len = file.length();

     //一块的大小
     int blockSize = 15;

     //一共分多少块
        int size = (int)Math.ceil((len*1.0/blockSize));
        System.out.println(size);
        //起始位置
        int beginPos = 0;
        //实际大小
        int actualSize = (int)(blockSize > len?len:blockSize);
        for (int i = 0; i <size ; i++) {
            beginPos = i*blockSize;
            if (i == size-1) {
                actualSize = (int)len; //最后一块
            }else {
                //如果不是最后一块的话 把每一块的大小赋给实际大小
                //用总长度减去除了最后一块的大小就是最后一块的大小
                // 以此来给上面的最后一块
                actualSize = blockSize;
                len = len-actualSize;
            }
            System.out.println(i+"-->"+beginPos+"-->"+actualSize);
            testRandom2(i,beginPos,actualSize);
        }
    }
    public static void testRandom()throws IOException {
        RandomAccessFile raf = new RandomAccessFile(new File("src/cn/io/com/TestBufferReader.java"),"r");
        raf.seek(5);
        byte[] bytes = new byte[1024];
        int tmp;
        while((tmp = raf.read(bytes)) != -1) {
            System.out.println(new String(bytes,0,tmp));
        }
        raf.close();
    }
    public static void testRandom2(int i,int beginPos,int actualSize) throws IOException{
        //分块思想 起始位置 和实际大小
        RandomAccessFile raf = new RandomAccessFile(new File("src/cn/io/com/TestBufferReader.java"),"r");
//        //给定一个起始位置
//        int beginPos = 1030;
//        //要一个实际大小
//        int actualSize = 1025;
        raf.seek(beginPos);
        byte[] bytes = new byte[1024];
        int tmp;
        while((tmp = raf.read(bytes)) != -1) {
            if (tmp < actualSize) {
                System.out.println(new String(bytes,0,tmp));
                actualSize = actualSize-tmp;
            }else {
                System.out.println(new String(bytes,0,actualSize));
                break;
            }
        }
        raf.close();
    }
}
