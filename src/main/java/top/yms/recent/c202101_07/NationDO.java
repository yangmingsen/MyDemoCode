package top.yms.recent.c202101_07;
import java.io.Serializable;
import java.util.Date;


/**
* 国家DO
* @author esr
* @date: 2021年03月10日
*/
public class NationDO implements Serializable {
   private static final long serialVersionUID = 1L;

   /*唯一编码*/
   private String code;
   /*国家*/
   private String nation;
   /*备注*/
   private String remark;
   /*状态*/
   private String status;
   /*创建人*/
   private String creator;
   /*创建时间*/
   private Date createdTime;
   /*更新人*/
   private String updater;
   /*更新时间*/
   private Date updatedTime;

   public String getCode() {
       return code;
   }
   public void setCode(String code) {
       this.code = code;
   }

   public String getNation() {
       return nation;
   }
   public void setNation(String nation) {
       this.nation = nation;
   }

   public String getRemark() {
       return remark;
   }
   public void setRemark(String remark) {
       this.remark = remark;
   }

   public String getStatus() {
       return status;
   }
   public void setStatus(String status) {
       this.status = status;
   }

   public String getCreator() {
       return creator;
   }
   public void setCreator(String creator) {
       this.creator = creator;
   }

   public Date getCreatedTime() {
       return createdTime;
   }
   public void setCreatedTime(Date createdTime) {
       this.createdTime = createdTime;
   }

   public String getUpdater() {
       return updater;
   }
   public void setUpdater(String updater) {
       this.updater = updater;
   }

   public Date getUpdatedTime() {
       return updatedTime;
   }
   public void setUpdatedTime(Date updatedTime) {
       this.updatedTime = updatedTime;
   }


}