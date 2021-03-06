package study.daydayup.wolf.common.util.finance.installment;

import lombok.Data;
import study.daydayup.wolf.common.lang.enums.unit.RateEnum;
import study.daydayup.wolf.common.model.type.number.Rate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.common.util.finance
 *
 * @author Wingle
 * @since 2019/12/19 9:59 下午
 **/
@Data
public class RateInstallment {
    private long total;
    private long remain;
    private int nums;
    private int step;
    private List<Long> splitList;

    public RateInstallment(long total, int nums) {
        if (nums <= 0 || total <= 0) {
            throw  new IllegalArgumentException("installment args can't less than 0");
        }

        this.total = total;
        this.remain = total;
        this.nums = nums;
        splitList = new ArrayList<>();
    }

    public long split(int rate) {
        return split(rate, RateEnum.PER_MILLION);
    }

    public long split(int rate, RateEnum unit) {
        if (0 == rate) {
            return 0;
        }
        return split(new Rate(rate, unit));
    }

    public long split(Rate rate) {
        if (remain <= 0 || null == rate) {
            return 0;
        }

        long result;

        step++;
        if (step == nums) {
            result = remain;
            handleRemain(result);
            return result;
        }

        BigDecimal slice = new BigDecimal(total);
        slice = slice.multiply(rate.toBigDecimal());

        result = slice.longValue();
        if (result > remain) {
            result = remain;
        }

        handleRemain(result);
        return result;
    }

    private void handleRemain(long result) {
        splitList.add(result);
        remain = remain - result;
    }

}
