package top.yms.oa;

public class TheYResult {

    private boolean success; // 是否操作成功
    private String message; // 返回消息
    private Object result; // 返回附件的对象
    private String detail;

    public String parseException(Exception e) {
        if (e == null) return "null";
        StringBuffer tmpStr = new StringBuffer();
        String exceptionName = e.getClass().getName();
        String cause = e.getMessage();
        tmpStr.append(exceptionName).append(" : ").append(cause).append("\n");

        for (StackTraceElement stackTrace : e.getStackTrace()) {
            tmpStr.append("\t^ ").append(stackTrace.toString()).append("\n");
        }

        detail = tmpStr.toString();
        return detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public TheYResult() {

    }

    public TheYResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public TheYResult(boolean success, String message, Exception e) {
        this.success = success;
        this.message = message;
        parseException(e);
        this.result = e;
    }

    public TheYResult(boolean success, String message, Object result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    //一定要加toString, 不然看不到内容
    @Override
    public String toString() {
        return "ReqResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
