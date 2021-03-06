package study.daydayup.wolf.common.lang.enums.finance;

import lombok.Getter;
import study.daydayup.wolf.common.lang.enums.CodeBasedEnum;

/**
 * study.daydayup.wolf.common.lang.enums
 *
 * @author Wingle
 * @since 2019/9/29 4:51 PM
 **/
@Getter
public enum PrepayStrategyEnum implements CodeBasedEnum {
    NOT_ALLOW(3, "不允许"),
    ALLOW(2, "请允许"),
    CONTRACT(1, "约定还款")
    ;

    private int code;
    private String desc;

    PrepayStrategyEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
