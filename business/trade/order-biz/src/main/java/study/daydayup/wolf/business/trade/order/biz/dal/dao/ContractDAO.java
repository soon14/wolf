package study.daydayup.wolf.business.trade.order.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.ContractDO;

@Mapper
public interface ContractDAO {
    Long insert(ContractDO record);

    Long insertSelective(ContractDO record);

    ContractDO selectById(Long id);

    int updateByIdSelective(ContractDO record);

    int updateById(ContractDO record);
}