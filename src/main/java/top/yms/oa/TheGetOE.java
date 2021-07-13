package top.yms.oa;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheGetOE {

    static class TempSysOrgElement implements Serializable {
        private String fdId;
        private String fd_no;
        private String fdKeyword;
        private String deptName;

        public TempSysOrgElement(String fdId, String fd_no, String fdKeyword) {
            this.fdId = fdId;
            this.fd_no = fd_no;
            this.fdKeyword = fdKeyword;

        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getFdKeyword() {
            return fdKeyword;
        }

        public void setFdKeyword(String fdKeyword) {
            this.fdKeyword = fdKeyword;
        }

        public String getFdId() {
            return fdId;
        }

        public void setFdId(String fdId) {
            this.fdId = fdId;
        }

        public String getFd_no() {
            return fd_no;
        }

        public void setFd_no(String fd_no) {
            this.fd_no = fd_no;
        }

        @Override
        public String toString() {
            return "TempSysOrgElement{" +
                    "fdId='" + fdId + '\'' +
                    ", fd_no='" + fd_no + '\'' +
                    ", fdKeyword='" + fdKeyword + '\'' +
                    ", deptName='" + deptName + '\'' +
                    '}';
        }
    }


    final String theFindNotifyInfoSql = "SELECT the_inst_fd_id,the_org_ele_fd_id,the_org_ele_name FROM `km_inst_knowledge_extend` WHERE `the_inst_fd_id`=?  LIMIT 0,1000";
    final String fDeptInfoSql = "SELECT fd_id,fd_org_type,fd_no,fd_keyword FROM sys_org_element WHERE fd_parentid=? and fd_is_available=1";
    final String fPnoFromPostSql = "SELECT fd_id,fd_org_type,fd_no,fd_keyword FROM sys_org_element WHERE fd_id IN ( SELECT fd_personid FROM `sys_org_post_person` WHERE fd_postid = ? )";


    private void doSubSearch(String orgEleId, Map<String, TempSysOrgElement> theResultMap) throws Exception{
        ResultSet theOrgEleResultSet = TheYJUtil.doGetResultSet(fDeptInfoSql, orgEleId);

        while (theOrgEleResultSet.next()) {
            int orgType = theOrgEleResultSet.getInt("fd_org_type");

            String fdId = theOrgEleResultSet.getString("fd_id");
            if (orgType == 8) { //如果是直接目标(人) 直接添加
                TempSysOrgElement tse = new TempSysOrgElement(
                        fdId,
                        theOrgEleResultSet.getString("fd_no"),
                        theOrgEleResultSet.getString("fd_keyword")
                );
                theResultMap.put(fdId, tse);
            } else if (orgType == 4) {
                ResultSet thePostResult = TheYJUtil.doGetResultSet(fPnoFromPostSql, fdId);
                while (thePostResult.next()) {
                    TempSysOrgElement tse = new TempSysOrgElement(
                            thePostResult.getString("fd_id"),
                            thePostResult.getString("fd_no"),
                            thePostResult.getString("fd_keyword")
                    );

                    theResultMap.put(tse.getFdId(), tse);
                }

            } else if (orgType == 2) {
                doSubSearch(fdId, theResultMap);
            }

        }


    }

    private List<TempSysOrgElement> parseSysOrgElement1(String theInstFdId) throws Exception {
        ResultSet theDeptInfoResultSet = TheYJUtil.doGetResultSet(theFindNotifyInfoSql, theInstFdId);

        Map<String, TempSysOrgElement> theResultMap = new HashMap<>();
        while (theDeptInfoResultSet.next()) { // orgType=2
            String theDeptId = theDeptInfoResultSet.getString("the_org_ele_fd_id");
            if (!StringUtils.isBlank(theDeptId)) {
                doSubSearch(theDeptId, theResultMap);
            }
        }

        return new ArrayList<TempSysOrgElement>() {{
            for(Map.Entry<String, TempSysOrgElement> entry : theResultMap.entrySet()) {
                add(entry.getValue());
            }
        }};
    }


    public static void main(String[] args) throws Exception{
        TheGetOE theGetOE = new TheGetOE();
        String theInstFdId = "179ac626ab13f1e5d12df3046969b0ea"; //inst id
        List<TempSysOrgElement> tempSysOrgElements = theGetOE.parseSysOrgElement1(theInstFdId);

        final String sql = "SELECT fd_name from sys_org_element where fd_id = ?";
        tempSysOrgElements.forEach(ele -> {
            try {
                ResultSet rs = TheYJUtil.doGetResultSet(sql, ele.getFdId());
                while (rs.next()) {
                    String name = rs.getString("fd_name");
                    ele.setDeptName(name);
                    break;
                }
                System.out.println(ele);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

}
