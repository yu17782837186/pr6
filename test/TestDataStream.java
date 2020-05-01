package cn.io.com;
import java.io.*;
import java.util.Date;

public class TestDataStream {
    public static void main(String[] args) {
        //1 对象流  先写出，后读取
        //2 读取的顺序与写出的保持一致
        //3 不是所有的对象都可以序列化 必须实现Sreializable接口
        //写出   序列化
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("zxc.jre"));
            oos.writeUTF("每一天都应该为了自己更加努力");
            oos.writeInt(33);
            oos.writeChar('n');
            oos.writeBoolean(true);
            oos.writeObject("找到一份好的工作");
            Employee employee = new Employee("小红",18);
            oos.writeObject(employee);

            ois = new ObjectInputStream(new FileInputStream("zxc.jre"));
            String str = ois.readUTF();
            System.out.println(str);
            int age = ois.readInt();
            System.out.println(age);
            char ch = ois.readChar();
            System.out.println(ch);
            boolean flag = ois.readBoolean();
            System.out.println(flag);
            try {
                Object str2 = ois.readObject();
                Object employee2 = ois.readObject();
                if (str2 instanceof String) {
                    String string = (String) str2;
                    System.out.println(string);
                }
                if (employee2 instanceof Employee) {
                    Employee employ = (Employee) employee2;
                    System.out.println(employ.getName()+"--->"+employ.getSalary());
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
            try {
                if (oos != null) {
                    oos.close();
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void testData() {
        //写出
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos));
        //操作数据类型+数据 任何的数据都可以转成字节数组 转换成字节数组/占用了多少个字节
        //  不需要关闭流
        try {
            dos.writeUTF("我太难了");
            dos.writeInt(18);
            dos.writeBoolean(true);
            dos.writeChar('z');
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] datas = baos.toByteArray();
        System.out.println(datas.length);
        //读取
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
        //顺序与写出一致
        try {
            String str = dis.readUTF();
            int age = dis.readInt();
            boolean flag = dis.readBoolean();
            char ch = dis.readChar();
            System.out.println(age);
        }catch(IOException e) {
            e.printStackTrace();
        }

    }
    public static void testSerializable() {
        //1 对象流  先写出，后读取
        //2 读取的顺序与写出的保持一致
        //3 不是所有的对象都可以序列化 必须实现Sreializable接口
        //写出   序列化
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeUTF("展示你的代码");
            oos.writeInt(16);
            oos.writeChar('N');
            oos.writeBoolean(false);
            oos.writeObject("少说话，多敲代码");
            oos.writeObject(new Date());
            Employee emp = new Employee("小明",18);
            oos.writeObject(emp);
            oos.flush();

            byte[] bytes = baos.toByteArray();
            //读取  反序列化
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);

            String str = ois.readUTF();
            int age = ois.readInt();
            char ch = ois.readChar();
            boolean flag = ois.readBoolean();
            //对象的数据还原
            Object str1 = ois.readObject();
            Object date = ois.readObject();
            Object employee = ois.readObject();
            if (str1 instanceof String) {
                String strStr1 = (String)str1;
                System.out.println(strStr1);
            }
            if (date instanceof Date) {
                Date dateDate = (Date)date;
                System.out.println(dateDate);
            }
            if (employee instanceof Employee) {
                Employee employee1 = (Employee) employee;
                System.out.println(employee1.getName()+"---->"+employee1.getSalary());
            }
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Employee implements Serializable{
    private  String name; //该数据不需要序列化 transient
    private  double salary;

    public Employee() {

    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
