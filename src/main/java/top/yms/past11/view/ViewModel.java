package top.yms.past11.view;

public class ViewModel {
    private int code;
    private String classs;
    private String top;
    private String left;

    public ViewModel(int code, String classs, String top, String left) {
        this.code = code;
        this.classs = classs;
        this.top = top;
        this.left = left;
    }

    @Override
    public String toString() {
        return "{" +
                "code: \'" + code +"\'"+
                ", class: \'" + classs + '\'' +
                ", top: \'" + top +"\'"+
                ", left: \'" + left +"\'"+
                "},";
    }
}
