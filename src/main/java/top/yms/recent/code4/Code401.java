package top.yms.recent.code4;

public class Code401 {
    public static void main(String[] args) {
        String content = "hello world，铭森. this is word";
        Integer key = 1000;

        // 用户A发消息前，通过某种方式加密
        String encryptStr = xor(content,key);
        System.out.println(encryptStr);

        // 密文通过网络传输

        // 用户B收到密文后使用相同的方式解密
        String decryptStr = xor(encryptStr,key);
        System.out.println(decryptStr);
    }

    /**
     * 加密算法及秘钥
     * @param content
     * @return
     */
    public static String  xor(String content, Integer key){
        char[] chars = content.toCharArray();
        for(int i=0; i<chars.length; i++){
            chars[i] = (char)  (chars[i]^key) ;
        }
        return new String(chars);
    }

}
