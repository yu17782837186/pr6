package cn.io.com;
import java.io.*;
import java.net.URL;

public class TestConvertStream {
    public static void main(String[] args) {
        //以字符流的形式操作字节流(纯文本)
        //指定字符集
        //test01();
        //test02();
        //test03();
        convert();
    }
    public static void convert() {
        try(BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in)) ;
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String str ="";
            while(!str.equals("end")) {
                str = reader.readLine(); //逐行读取
                writer.write(str); //逐行写出
                writer.newLine();
                writer.flush();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public static void test01() {
        try(InputStream is = new URL("http://www.baidu.com").openStream()) {
            int tmp;
            while((tmp =is.read()) != -1) {
                System.out.print((char) tmp); //发生乱码的原因是字节数不够
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public static void test02() {
        try(InputStreamReader is = new InputStreamReader(new URL("http://www.baidu.com").openStream(),"UTF-8")) {
            int tmp;
            while((tmp = is.read()) != -1) {
                System.out.print((char) tmp);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public static void test03() {
        try(BufferedReader reader = new BufferedReader
                (new InputStreamReader(new URL("http://www.baidu.com").openStream(),"UTF-8"))) {
            String str = null;
            while((str = reader.readLine()) != null) {
                System.out.println(str);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public static void test04() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader
                (new URL("http://www.baidu.com").openStream(),"UTF-8"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter
                    (new FileOutputStream("baidu.html"),"UTF-8"))) {
            String  str; //发生乱码的原因是没有指定的字符集
            while((str =reader.readLine()) != null) {
                writer.write(str);
                writer.newLine();
            }
            writer.flush();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
