package study.daydayup.wolf.business.trade.api.domain.enums.loan;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 *
 * @author Wingle
 * @since 2019/10/5 6:55 PM
 **/
@Getter
public enum LoanAmountStrategyEnum implements CodeBasedEnum {
    NO_NEED_TO_PAY(40, "无需支付"),
    PAID(30, "已支付"),
    PART_PAID(20, "部分支付"),
    WAIT_TO_PAY(10, "待支付")
    ;

    private int   code;
    private String desc;
    LoanAmountStrategyEnum(int   code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
