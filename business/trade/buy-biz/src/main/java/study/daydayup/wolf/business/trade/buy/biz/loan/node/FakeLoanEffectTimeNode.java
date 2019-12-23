package study.daydayup.wolf.business.trade.buy.biz.loan.node;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.entity.Contract;
import study.daydayup.wolf.business.trade.api.vo.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.common.context.BuyContext;
import study.daydayup.wolf.business.trade.buy.biz.common.node.AbstractTradeNode;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.node
 *
 * @author Wingle
 * @since 2019/12/17 8:19 下午
 **/
@Component
public class FakeLoanEffectTimeNode extends AbstractTradeNode implements TradeNode {

    @Override
    public void run(BuyContext context) {
        init(context);

        List<InstallmentTerm> terms = context.getContract().getInstallmentTermList();
        if (terms == null || terms.isEmpty()) {
            return;
        }



    }

}
