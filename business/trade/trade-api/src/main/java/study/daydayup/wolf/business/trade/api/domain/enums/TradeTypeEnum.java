package study.daydayup.wolf.business.trade.api.domain.enums;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 *
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum TradeTypeEnum implements CodeBasedEnum {
    FENXIAO(100, "分销"),

    GIFT(90, "送礼"),
    GIFT_RECEIVE(80, "收礼"),

    SECKILL(70, "秒杀"),

    PEERPAY(60, "代付"),
    PEERPAY_PAY(50, "代付支付"),

    GROUP_BUY(40, "团购"),
    GROUP_PARTICIPATE(30, "团购参与"),



    OVERDUE_REPAY(16, "逾期还款订单"),
    PREPAY_All(15, "提前还款订单"),
    PREPAY_ORDER(14, "提前还款订单"),
    COLLECTION_ORDER(13, "催收订单"),
    LOAN_PROXY(12, "放款代理"),
    REPAY_ORDER(11, "还款订单"),
    LOAN_ORDER(10, "放款订单"),

    REPAY_TERM(5, "催收订单"),
    INSTALLMENT_TERM(4, "催收订单"),
    LOAN_CONTRACT(3, "借款合同"),

    CONTRACT(2, "普通合同"),
    ORDER(1, "普通订单")
    ;

    private int code;
    private String desc;
    TradeTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
