package top.yms.recent.c202201;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Code20220413 {


    @Test
    public void doTest1() {
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile() throws IOException {
        String fileName = "G:\\Project\\Java\\other\\MyDemoCode\\src\\main\\java\\top\\yms\\recent\\c20220118\\Code20220118.java";
        FileInputStream is = new FileInputStream(fileName);

        byte [] bytes = new byte[16];
        int b = -1;
        while ((b = is.read(bytes)) > 0) {
            System.out.print(new String(bytes, 0, b));
        }
        is.close();
    }

    static class User {
        int id;
        String name;
        List<String> hobby;

        public User(int id, String name, List<String> hobby) {
            this.id = id;
            this.name = name;
            this.hobby = hobby;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }



    @Test
    public void doTest(  )
    {
        System.out.println( "Hello World!" );

        // 初始化测试数据
        List<String> hobby1 = Arrays.asList("java", "c", "音乐");
        List<String> hobby2 = Arrays.asList("c++", "c", "游戏");
        User user1 = new User(1, "张三", hobby1);
        User user2 = new User(2, "李四", hobby2);
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);


        List<String> collect = hobby1.stream().map(x -> x + "Abc=> ").collect(Collectors.toList());

        int [] arr1 = {1,3,5,7,9};
        int [] arr2 = {2,4,6,8,10};


        List<int[]> ints = Arrays.asList(arr1, arr2);
        ints.stream().flatMapToInt(x -> Arrays.stream(x)).forEach(x -> System.out.println(x));



        // 将集合中每个用户的爱好进行计算，取并集
        List<String> result = users.stream()
                .map(user -> user.hobby)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result);


    }
}
