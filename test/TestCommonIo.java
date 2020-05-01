package cn.io.com;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import java.io.File;
import java.util.Collection;

public class TestCommonIo {
    public static void main(String[] args) {
        //文件的大小
        long len = FileUtils.sizeOf(new File("src/cn/io/com/TestRandStream2.java"));
        System.out.println(len);
        //文件夹的大小
        len = FileUtils.sizeOf(new File("F:/java code/io2"));
        System.out.println(len);
        //列出子孙级 操作一层
        Collection<File> files = FileUtils.listFiles(new File("F:/java code/io2"), EmptyFileFilter.NOT_EMPTY,null);
        for (File f:files) {
            System.out.println(f.getAbsolutePath());
        }
        System.out.println("--------------");
        //操作所有的子孙级文件
        files = FileUtils.listFiles(new File("F:/java code/io2"),EmptyFileFilter.NOT_EMPTY, DirectoryFileFilter.INSTANCE);
        for (File ff:files) {
            System.out.println(ff.getAbsolutePath());
        }
        System.out.println("---------------");
        //操作所有的子孙级文件并且后缀为java的
        files = FileUtils.listFiles(new File("F:/java code/io2"),new SuffixFileFilter("class"),DirectoryFileFilter.INSTANCE);
        for (File fff:files) {
            System.out.println(fff.getAbsolutePath());
        }
        System.out.println("----------------");
        //操作所有的子孙级文件并且后缀为java或者class的
        files = FileUtils.listFiles(new File("F:/java code/io2"),FileFilterUtils.or(new SuffixFileFilter("class"),new SuffixFileFilter("java"),EmptyFileFilter.EMPTY),DirectoryFileFilter.INSTANCE);
        for (File f1 : files) {
            System.out.println(f1.getAbsolutePath());
        }
        System.out.println("--------------");
        //操作所有的子孙级文件并且后缀为class并不为空的文件
        files = FileUtils.listFiles(new File("F:/java code/io2"),FileFilterUtils.and(new SuffixFileFilter("class"),EmptyFileFilter.EMPTY),DirectoryFileFilter.INSTANCE);
        for (File f2:files) {
            System.out.println(f2.getAbsolutePath());
        }
    }
}
