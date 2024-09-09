

import java.io.File;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        //创建file对象
        File parentFile = new File("D:\\FileStudy");
        File file1 = new File(parentFile, "test1.txt");
        File file2= new File("D:\\FileStudy", "test2.html");
        File file3 = new File("D:\\FileStudy\\test3.java");
        System.out.println("file3 = " + file3);
        //\\反斜杠\:转义符
        System.out.println("台湾\"总统\"");
        //代码到此，并不会在硬盘中创建这个文件
        //这个file对象目前还在计算机内存中
        boolean isOk1 =false,isOk2= false,isOk3= false;
        try {
            isOk1 = file1.createNewFile();//创建一个空文件
            isOk2 = file2.createNewFile();
            isOk3 = file3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isOk1&&isOk2&&isOk3){
            System.out.println("三个文件都创建成功！");
        }

    }
}
