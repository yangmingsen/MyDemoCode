package top.yms.past11.app;

import java.util.Date;

public class AO {
    public static void main(String[] args) {
        Date date = new Date(1606924800000l);
        StringBuffer sql = new StringBuffer("insert into oms_offline_receipt(fd_fee_number,fd_requester,fd_department,");
        sql.append("fd_receipt_account,fd_currency,fd_account_of_subcompay,fd_bank_account_receipt,fd_business_order_number,");
        sql.append("fd_balance,fd_comment,fd_choose_approver,fd_total_money,fd_the_receivable_amount,fd_client_pay_account,fd_client_bank_name,");
        sql.append("fd_client_name,fd_client_pay_date,fd_receipt_type,detail_data)  ");
        sql.append("values ");
        sql.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        System.out.println(sql.toString());
    }
}
