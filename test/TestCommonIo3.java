package cn.io.com;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class TestCommonIo3 {
    public static void main(String[] args) throws IOException {
        //拷贝文件
        FileUtils.copyFile(new File("emp.txt"),new File("empcopy.txt"));
        //拷贝文件到目录
        FileUtils.copyFileToDirectory(new File("emp.txt"),new File("lib"));
        //拷贝文件到文件 前者是后者的子目录
        FileUtils.copyDirectoryToDirectory(new File("lib"),new File("lib2"));
        //把文件全部拷贝过来
        FileUtils.copyDirectory(new File("lib"),new File("lib2"));

    }
}
