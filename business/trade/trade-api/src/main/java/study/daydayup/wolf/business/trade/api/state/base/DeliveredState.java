package study.daydayup.wolf.business.trade.api.state.base;

import study.daydayup.wolf.business.trade.api.state.AbstractTradeState;
import study.daydayup.wolf.business.trade.api.state.TradeState;

/**
 * study.daydayup.wolf.business.trade.api.state
 *
 * @author Wingle
 * @since 2019/10/5 11:24 PM
 **/
public class DeliveredState extends AbstractTradeState implements TradeState {
    protected int code = 20;
}
