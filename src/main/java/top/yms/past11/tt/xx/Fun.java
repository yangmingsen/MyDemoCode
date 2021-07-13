package top.yms.past11.tt.xx;

import java.util.LinkedHashMap;
import java.util.Map;

public class Fun {

    static class Setting {
        String code;
        String subCode;
        String value;
        Integer type;

        public Setting(String code, String subCode, String value, Integer type) {
            this.code = code;
            this.subCode = subCode;
            this.value = value;
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getSubCode() {
            return subCode;
        }

        public void setSubCode(String subCode) {
            this.subCode = subCode;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Setting{" +
                    "code='" + code + '\'' +
                    ", subCode='" + subCode + '\'' +
                    ", value='" + value + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    public static Setting findParent(Setting setting, Map<String, Setting> map) {
        String subCode = setting.getSubCode();
        Setting currentSetting = map.get(subCode);
        if (currentSetting != null) {
            Setting resSetting = findParent(currentSetting, map);
            setting.setSubCode(resSetting.getSubCode());
        }
        return setting;
    }



    public static void main(String[] args) {
        Map<String, Setting> map = new LinkedHashMap<>();
        map.put("1", new Setting("1","09434","one",0));
        map.put("2", new Setting("2","1","Two",1));
        map.put("3", new Setting("3","2","Three",1));
        map.put("4", new Setting("4","3","Four",1));

        Setting setting = map.get("4");
        findParent(setting,map);

        for(Map.Entry<String, Setting> entry : map.entrySet()) {
            System.out.println(entry.getValue().toString());
        }

    }

}