package top.yms.recent.code7;


import java.util.Stack;

public class Code709 {

    public static void main(String[] args) throws Exception {
        Stack[] stacks = new Stack[10];

        stacks[0] = new Stack<>();
        stacks[0].push("sdf");
        stacks[0].push(234);
        stacks[0].push(new char[10]);


    }

    private void parseSucess() throws Exception {
        try {
            int x = 1/0;
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("NO sucess");
            throw new Exception(e);
        }
    }

}
