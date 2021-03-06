package study.daydayup.wolf.business.trade.api.domain.enums.order;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 *
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum OrderLineTypeEnum implements CodeBasedEnum {
    ORDER(20, "订单"),
    CONTRACT(10, "合同"),
    UNKNOWN(0, "未知交易"),
    ;

    private int   code;
    private String desc;
    OrderLineTypeEnum(int   code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
