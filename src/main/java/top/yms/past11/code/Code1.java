package top.yms.past11.code;


public class Code1 {
    static class MyString {
        private String str;

        public MyString(String str) {
            this.str = str;
        }

        public String getStr() {
            return str;
        }

        public  void setStr(String str) {
            System.out.println(Thread.currentThread()+" come in");
            synchronized (this) {
                this.str = str;
                try {
                    Thread.sleep(5*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyThread extends Thread {
        private MyString myString;
        public MyThread(MyString myString) {
            this.myString = myString;
        }
        @Override
        public void run() {
                myString.setStr("234325");
                System.out.println(Thread.currentThread()+" 修改为"+myString.getStr());
        }
    }

    public static void main(String[] args) {
        MyString myString = new MyString("hello world");
        MyThread myThread = new MyThread(myString);
        MyThread myThread2 = new MyThread(myString);

        myThread.start();
        myThread2.start();



    }

}
