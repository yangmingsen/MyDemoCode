package top.yms.recent.c202110;

import org.junit.jupiter.api.Test;

public class Code20220125 {
    public enum ACTIONTYPE{
        END
        ,_START0       //Start
        ,_END0       //End
        ,_INVOKE0       //小额普通贷记往帐复核
        ,_ASSIGN0       //返回结果赋值
        ,_INVOKE2       //开始异步调用
    }

    protected void executeFlow(){
        ACTIONTYPE nextAction = Enum.valueOf(ACTIONTYPE.class, "_START0");
        while(nextAction!=ACTIONTYPE.END){
            switch(nextAction){
                case _START0:  //Start
                    System.out.println("_START0");
                    break;
                case _END0:  //End
                    System.out.println("_END0");
                    break;
                case _INVOKE0:  //小额普通贷记往帐复核
                    System.out.println("_INVOKE0");
                    break;
                case _ASSIGN0:  //返回结果赋值
                    System.out.println("_ASSIGN0");
                    break;
                case _INVOKE2:  //开始异步调用
                    System.out.println("_INVOKE2");
                    break;
                case END:  System.out.println("END");
                    //finish
                    break;
                default:
                    try {
                        throw new Exception("Exception="+nextAction.name());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    @Test
    public void doTest() {
        executeFlow();
    }

}
