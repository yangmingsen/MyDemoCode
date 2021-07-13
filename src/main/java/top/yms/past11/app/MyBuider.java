package top.yms.past11.app;

public class MyBuider {

    private String name;
    private String age;
    private Integer sex;

    @Override
    public String toString() {
        return "MyBuider{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex=" + sex +
                '}';
    }

    private MyBuider(){}

    public static class Builder {
        private final MyBuider buider = new MyBuider();
        public Builder() {
        }

        public Builder setName(String name) {
            this.buider.name = name;
            return this;
        }

        public Builder setAge(String age) {
            this.buider.age = age;
            return this;
        }

        public Builder setSex(Integer sex) {
            this.buider.sex = sex;
            return this;
        }

        public MyBuider build() {return buider;}
    }




}
