package study.daydayup.wolf.business.goods.biz.api;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.goods.api.dto.request.GoodsOption;
import study.daydayup.wolf.business.goods.api.entity.goods.LoanGoods;
import study.daydayup.wolf.business.goods.api.service.LoanService;
import study.daydayup.wolf.business.goods.biz.loan.LoanEntity;
import study.daydayup.wolf.business.goods.biz.loan.LoanRepository;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.goods.biz.api
 *
 * @author Wingle
 * @since 2019/12/10 8:52 下午
 **/
@RpcService(protocol = "dubbo")
public class LoanServiceImpl implements LoanService {
    @Resource
    private LoanRepository repository;

    @Override
    public long create(LoanGoods goods) {
        LoanEntity entity = new LoanEntity();
        BeanUtils.copyProperties(goods, entity);

        return repository.save(entity);
    }

    @Override
    public LoanGoods findById(long goodsId, long orgId, GoodsOption option) {
        LoanEntity entity = repository.findById(goodsId, orgId);
        if (entity == null) {
            return null;
        }

        return (LoanGoods) entity;
    }

    @Override
    public List<LoanGoods> findByOrgId(long orgId) {
        List<LoanEntity> entityList = repository.findByOrgId(orgId);
        if (entityList.isEmpty()) {
            return new ArrayList<LoanGoods>();
        }

        return new ArrayList<LoanGoods>(entityList);
    }

    @Override
    public int modify(LoanGoods goods) {
        return 0;
    }
}
