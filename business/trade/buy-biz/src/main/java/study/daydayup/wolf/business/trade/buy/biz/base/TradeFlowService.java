package study.daydayup.wolf.business.trade.buy.biz.base;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayNotifyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayNotifyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PreviewResponse;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.common.util.EnumUtil;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.buy.domain.service.impl
 *
 * @author Wingle
 * @since 2019/10/9 2:30 下午
 **/
@Component
public class TradeFlowService {
    @Resource
    private TradeFlowFactory flowFactory;

    public PreviewResponse preview(BuyRequest request) {
        TradeTypeEnum tradeType = EnumUtil.codeOf(request.getTradeType(), TradeTypeEnum.class);
        TradeFlow tradeFlow = flowFactory.create(tradeType);

        return tradeFlow.preview(request);
    }

    public ConfirmResponse confirm(BuyRequest request) {
        TradeTypeEnum tradeType = EnumUtil.codeOf(request.getTradeType(), TradeTypeEnum.class);
        TradeFlow tradeFlow = flowFactory.create(tradeType);
        tradeFlow.buildConfirmFlow();

        return tradeFlow.confirm(request);
    }

    public PayResponse pay(PayRequest request) {
        return null;
    }

    public PayNotifyResponse payNotify(PayNotifyRequest request) {
        return null;
    }

}
