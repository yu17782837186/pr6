package cn.io.com;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestCommonIo2 {
    public static void main(String[] args) throws IOException {
        //读取文件 读到字符串的方法需要指定字符集
        String str = FileUtils.readFileToString(new File("emp.txt"),"utf8");
        System.out.println(str);
        //读到字节数组里的不需要指定字符集
        byte[] bytes = FileUtils.readFileToByteArray(new File("emp.txt"));
        System.out.println(bytes.length);
        System.out.println("---------");
        //逐行读取
        List<String> msg = FileUtils.readLines(new File("emp.txt"),"utf8");
        for (String string :msg) {
            System.out.println(string);
        }
        System.out.println("----------");
        //迭代器的方式读取
        LineIterator it = FileUtils.lineIterator(new File("emp.txt"));
        while(it.hasNext()) {
            System.out.println(it.next());
        }

        //写出文件
        FileUtils.write(new File("emp2.txt"),"今天的我必须要很努力，才能对得起将来的我！！\n","utf8");
        FileUtils.writeStringToFile(new File("emp2.txt"),"我们都会感谢现在努力的自己\n","utf8",true);
        FileUtils.writeByteArrayToFile(new File("emp2.txt"),"现在要做的就是努力充实我们自己的大脑\n".getBytes("utf8"),true);

        //写出列表
        List<String> datas = new ArrayList<>();
        datas.add("小红");
        datas.add("小明");
        datas.add("小亮");
        FileUtils.writeLines(new File("emp2.txt"),datas,"\n",true);
    }
}
