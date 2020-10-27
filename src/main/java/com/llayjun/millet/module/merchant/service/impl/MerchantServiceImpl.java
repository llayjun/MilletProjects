package com.llayjun.millet.module.merchant.service.impl;

import com.llayjun.millet.module.merchant.entity.Merchant;
import com.llayjun.millet.module.merchant.mapper.MerchantMapper;
import com.llayjun.millet.module.merchant.service.IMerchantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llayjun.millet.module.merchant.vo.MerchantVO;
import com.llayjun.millet.module.task.service.IMerchantTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyl
 * @since 2020-10-26
 */
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements IMerchantService {

    @Autowired
    MerchantMapper merchantMapper;

    @Autowired
    IMerchantTaskService iMerchantTaskService;

    @Override
    public List<MerchantVO> getMerchantList() {
        List<MerchantVO> merchantVOList = merchantMapper.getMerchantList();
        // 商户累计数量
        for (MerchantVO merchantVO: merchantVOList) {
            Integer num = iMerchantTaskService.getMerchantTaskCount(merchantVO.getId());
            merchantVO.setMerchantTaskNum(num);
        }
        return merchantVOList;
    }
}
