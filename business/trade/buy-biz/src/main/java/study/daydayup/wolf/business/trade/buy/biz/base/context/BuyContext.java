package study.daydayup.wolf.business.trade.buy.biz.base.context;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Seller;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.TradeGoods;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.TradeUmp;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity
 *
 * @author Wingle
 * @since 2019/10/5 11:01 AM
 **/
@Data
@Builder
public class BuyContext {

    private TradeTypeEnum tradeType;
    private TradePhaseEnum tradePhase;

    private BuyRequest request;
    private Buyer buyer;
    private Seller seller;

    private List<TradeGoods> goodsList;
    private TradeUmp ump;

    private Contract contract;
    private Order order;
}
