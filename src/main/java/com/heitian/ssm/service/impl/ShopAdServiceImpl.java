package com.heitian.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import com.heitian.ssm.bo.ShopAdBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.ShopAdDao;
import com.heitian.ssm.service.ShopAdService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShopAdServiceImpl implements ShopAdService {

	@Resource
	private ShopAdDao shopAdDao;

	@Override
	public Result addShopAd(Long shopId, String photoUrl)
	{
		int i = shopAdDao.addShopAd(shopId, photoUrl);
		return returnRes(i);
	}

	@Override
	public Result showShopAdStatus(Long shopId)
	{
        Result result = new Result();
		Long status = shopAdDao.selectStatus(shopId);
        if(status == null) {
            result.setStatus(-1);
            result.setMessage("haven't applied");
        } else {
            result.setStatus(status.intValue());
            if(status == 0) {
                result.setMessage("wait for verified");
            } else if(status == 1) {
                result.setMessage("verified");
            } else if(status == 2) {
                result.setMessage("rejected");
            } else if(shopId == 3) {
                result.setMessage("out of date");
            }
        }
		return result;
	}

	@Override
	public Result deleteShopAd(Long id)
	{
		int i = shopAdDao.deleteShopAd(id);
		return returnRes(i);
	}

    @Override
    public Result changeShopAdStatus(Long id, Long status)
    {
        int i = shopAdDao.changeShopAdStatus(id, status);
        return returnRes(i);
    }

    public List<ShopAdBo> verifiedShopAd(){
        List<ShopAdBo> list = shopAdDao.verifiedShopAdBo();
        if(list == null)
            return new ArrayList<>();
        else return list;
    }

    public List<ShopAdBo> unverifiedShopAd(int page, int count){
        List<ShopAdBo> list = shopAdDao.unverifiedShopAdBo((page-1)*count, count);
        if(list == null)
            return new ArrayList<>();
        else return list;
    }

	private Result returnRes(int i)
	{
		Result result = new Result();
		if (i != 0) {
			result.setStatus(1);
			result.setMessage("success");
		} else {
			result.setMessage("failed");
			result.setStatus(0);
		}
		return result;
	}
}
