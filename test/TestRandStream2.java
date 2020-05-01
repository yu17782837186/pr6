package cn.io.com;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestRandStream2 {
    //源头
    private File srcFile;

    //目的地 是一个文件夹
    private String destDir;

    //所有分割后的文件存储路径
    private List<String> destPath;

    //每块大小
    private int blockSize;

    //块数：多少块
    private int size;

    public TestRandStream2(File srcFile,String destDir) {
        this(srcFile,destDir,1024);
    }

    public TestRandStream2(File srcFile,String destDir,int blockSize) {
        this.srcFile = srcFile;
        this.destDir = destDir;
        this.blockSize = blockSize;
        this.destPath = new ArrayList<>();
        init();
    }
    private void init() {
        //初始化
        //文件的大小
        long len = this.srcFile.length();
        
        //一共分多少块
        this.size = (int)Math.ceil((len*1.0/blockSize));

       //路径
        for (int i = 0; i < size; i++) {
            this.destPath.add(this.destDir+"/"+i+"-"+this.srcFile.getName());
        }
    }
    //合并
    public void add(String destFile)throws IOException {
        OutputStream os = new FileOutputStream(destFile,true);
        InputStream is = null;
        for (int i = 0; i <destPath.size(); i++) {
            is = new FileInputStream(destPath.get(i));
            byte[] bytes =new byte[1024];
            int tmp;
            while((tmp =is.read(bytes))!= -1) {
                os.write(bytes,0,tmp);
            }
        }
        os.flush();
        if (is != null) {
            is.close();
        }
        if (os != null) {
            os.close();
        }
    }
    public void spilt() throws IOException {
        //分割，计算每一块的起始位置和大小
        //文件的大小
        long len = this.srcFile.length();

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
            spiltDetail(i,beginPos,actualSize);
        }
    }
    private  void spiltDetail(int i, int beginPos, int actualSize) throws IOException {
        //分块思想 起始位置 和实际大小
        RandomAccessFile raf = new RandomAccessFile(this.srcFile,"r");
        RandomAccessFile raf2 = new RandomAccessFile(this.destPath.get(i),"rw");
//        //给定一个起始位置
//        int beginPos = 1030;
//        //要一个实际大小
//        int actualSize = 1025;
        raf.seek(beginPos);
        byte[] bytes = new byte[1024];
        int tmp;
        while((tmp = raf.read(bytes)) != -1) {
            if (tmp < actualSize) {
                //System.out.println(new String(bytes,0,tmp));
                raf2.write(bytes,0,tmp);

                actualSize = actualSize-tmp;
            }else {
                //System.out.println(new String(bytes,0,actualSize));
                raf2.write(bytes,0,actualSize);
                break;
            }
        }
        raf2.close();
        raf.close();
    }

    public static void main(String[] args) throws IOException {
        TestRandStream2 trs = new TestRandStream2(new File("src/cn/io/com/TestRandStream2.java"),"dest2");
        trs.spilt();
        trs.add("dest2/aaa.txt");
    }
}
