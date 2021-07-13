package top.yms.past11.tt;

class AXA {
    private String username;
    private String password;
    private Integer sex;
    private String bir;

    public AXA setUsername(String username) {
        this.username = username;
        return this;
    }

    public AXA setPassword(String password) {
        this.password = password;
        return this;
    }

    public AXA setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public AXA setBir(String bir) {
        this.bir = bir;
        return this;
    }

    @Override
    public String toString() {
        return "AXA{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", bir='" + bir + '\'' +
                '}';
    }
}
public class OSD {
    public static void main(String[] args) {
        final AXA axa = new AXA();
        axa.setBir("sdf").setPassword("235").setSex(1).setUsername("wokdsf");
        System.out.println(axa);
    }
}
