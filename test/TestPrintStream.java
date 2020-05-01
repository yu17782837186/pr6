package cn.io.com;
import java.io.*;
public class TestPrintStream {
    public static void main(String[] args) throws FileNotFoundException {
        //打印流 PrintStream和PrintWriter都有自动刷新
        PrintStream ps = System.out;
        ps.println(18);
        ps.println(true);
        ps.println('z');
        ps = new PrintStream(new BufferedOutputStream(new FileOutputStream("print.txt")),true);
        ps.println("我要努力敲代码");
        ps.println(0);
        ps.close();
        //重定向  将输出内容打印到文件中
        System.setOut(ps);
        System.out.println("少说话，多敲代码！！");
        //重定向回来
        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)),true));
        System.out.println("i am working hard...");

        PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream("print.txt")),true);
        pw.println("学习io");
        pw.println(1800);
    }
}
