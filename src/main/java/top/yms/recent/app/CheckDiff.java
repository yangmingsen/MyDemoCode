package top.yms.recent.app;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CheckDiff {
    private String []  getHvpsData() {
        String str = "FCBPDT\n" +
                "FCBPSQ\n" +
                "MESGTP\n" +
                "MSETDT\n" +
                "IOTYPE\n" +
                "MSETSQ\n" +
                "MESGTM\n" +
                "PROCST\n" +
                "TRANST\n" +
                "ETOEID\n" +
                "BUSITP\n" +
                "BUSILS\n" +
                "CRDBTG\n" +
                "CRCYCD\n" +
                "FETYCD\n" +
                "TRANAM\n" +
                "AFEETG\n" +
                "AFEEAM\n" +
                "FEEAM1\n" +
                "FEEAM2\n" +
                "FEEAM3\n" +
                "DCMTTP\n" +
                "DCMTNO\n" +
                "DRAWFS\n" +
                "PYPAWD\n" +
                "CPTYPE\n" +
                "PRIOTP\n" +
                "AGENOG\n" +
                "AGENNA\n" +
                "AGSEOG\n" +
                "AGSENA\n" +
                "PYERCL\n" +
                "PYERCD\n" +
                "PYERBK\n" +
                "PYERAC\n" +
                "PYERNA\n" +
                "PYERAD\n" +
                "PYEECL\n" +
                "PYEECD\n" +
                "PYEEBK\n" +
                "PYEEAC\n" +
                "PYEENA\n" +
                "PYEEAD\n" +
                "SETLDT\n" +
                "MMBIBK\n" +
                "SLICID\n" +
                "REMARK\n" +
                "REMRK2\n" +
                "PRINNM\n" +
                "BHPRTG\n" +
                "MODFAC\n" +
                "MODFNA\n" +
                "CITYCD\n" +
                "BRCHNO\n" +
                "USERID\n" +
                "CKBKUS\n" +
                "AUTHUS\n" +
                "VERSTG\n" +
                "CKPRST\n" +
                "KEEPDT\n" +
                "REJETX\n" +
                "STPSTG\n" +
                "ACPICD\n" +
                "TRANTG\n" +
                "CNCLFG\n" +
                "CNCLUS\n" +
                "SENDDT\n" +
                "CUSTTP\n" +
                "INNRTG\n" +
                "INNRAC\n" +
                "INNRNA\n" +
                "INTRID\n" +
                "DVPRST\n" +
                "DVPRCD\n" +
                "DVPRBR\n" +
                "DVPRUS\n" +
                "DVPRTM\n" +
                "AFEEAC\n" +
                "AFEENA\n" +
                "SUBACT\n" +
                "ORMEDT\n" +
                "ORMESQ\n" +
                "CHANEL\n" +
                "SBSQNO\n" +
                "TXARID\n" +
                "INACTM\n" +
                "PYEENM\n" +
                "PYERNM\n";

        return str.split("\n");
    }

    private String [] getBepsData() {
        String str = "FCBPDT\n" +
                "FCBPSQ\n" +
                "IOTYPE\n" +
                "MESGTP\n" +
                "DETLDT\n" +
                "DETLSQ\n" +
                "PTOPID\n" +
                "PROCST\n" +
                "TRANST\n" +
                "PYERCL\n" +
                "PYEECL\n" +
                "PYERCD\n" +
                "PYERBK\n" +
                "PYERAC\n" +
                "PYERNA\n" +
                "PYERAD\n" +
                "PYEECD\n" +
                "PYEEBK\n" +
                "PYEEAC\n" +
                "PYEENA\n" +
                "PYEEAD\n" +
                "CRCYCD\n" +
                "TRANAM\n" +
                "BUSITP\n" +
                "BUSILS\n" +
                "RETNST\n" +
                "REJECD\n" +
                "REJETX\n" +
                "USTRD1\n" +
                "USTRD2\n" +
                "REMARK\n" +
                "REMRK2\n" +
                "DEALTM\n" +
                "PRINNM\n" +
                "REFRDT\n" +
                "REFRSQ\n" +
                "CITYCD\n" +
                "BRCHNO\n" +
                "USERID\n" +
                "CKBKUS\n" +
                "AUTHUS\n" +
                "VERSTG\n" +
                "AFEETG\n" +
                "AFEEAM\n" +
                "FEEAM1\n" +
                "FEEAM2\n" +
                "FEEAM3\n" +
                "DCMTTP\n" +
                "DCMTNO\n" +
                "MODFAC\n" +
                "MODFNA\n" +
                "CKPRST\n" +
                "KEEPDT\n" +
                "STPSTG\n" +
                "ACPICD\n" +
                "STACNO\n" +
                "STACNA\n" +
                "STSENO\n" +
                "CUSTTP\n" +
                "TRANTG\n" +
                "CONTNO\n" +
                "CHANEL\n" +
                "SBSQNO\n" +
                "TXARID\n" +
                "BACHNO\n" +
                "INACTM\n" +
                "RPTMLT\n" +
                "IDTFNO\n" +
                "IDTFTP\n" +
                "RPFLAG\n" +
                "ACCTBR\n" +
                "DEALSQ\n" +
                "DEALDT\n" +
                "CHANSQ\n" +
                "PYEENM\n" +
                "PYERNM\n";
        return str.split("\n");
    }

    private void check() {
        String[] hvpsData = getHvpsData();
        String[] bepsData = getBepsData();

        Map<String,String> hvpsMap = new HashMap<>();
        for (String hvpsDatum : hvpsData) {
            hvpsMap.put(hvpsDatum, "");
        }

        Map<String, String> bepsMap = new HashMap<>();
        for (String bepsDatum : bepsData) {
            bepsMap.put(bepsDatum, "");
        }

        Map<String, String> rightMap = new HashMap<>();
        hvpsMap.forEach( (x,y) -> {
            if (bepsMap.get(x) != null) {
                rightMap.put(x, "");

            }
        });

        rightMap.forEach((x,y) -> {
            hvpsMap.remove(x);
            bepsMap.remove(x);
        });

        System.out.println("相同：");
        rightMap.forEach((x,y) -> System.out.print(x+","));
        System.out.println();

        System.out.println("hvps: ");
        hvpsMap.forEach((x,y) -> System.out.print(x+","));
        System.out.println();

        System.out.println("beps: ");
        bepsMap.forEach((x,y) -> System.out.print(x+","));
        System.out.println();


    }

    @Test
    public void test() {
        check();
    }

}
