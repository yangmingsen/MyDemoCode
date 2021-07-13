package top.yms.recent.code1;

import java.util.concurrent.atomic.AtomicInteger;

public class Code4 {
    /**
     * Volatile access methods are used for table elements as well as
     * elements of in-progress next table while resizing.  All uses of
     * the tab arguments must be null checked by callers.  All callers
     * also paranoically precheck that tab's length is not zero (or an
     * equivalent check), thus ensuring that any index argument taking
     * the form of a hash value anded with (length - 1) is a valid
     * index.  Note that, to be correct wrt arbitrary concurrency
     * errors by users, these checks must operate on local variables,
     * which accounts for some odd-looking inline assignments below.
     * Note that calls to setTabAt always occur within locked regions,
     * and so in principle require only release ordering, not
     * full volatile semantics, but are currently coded as volatile
     * writes to be conservative.
     */
    public static void main(String[] args) {
        AtomicInteger air = new AtomicInteger(0);
        int andIncrement = air.incrementAndGet();
        int res = air.get();
        System.out.println(andIncrement);
        System.out.println(res);
    }
}
