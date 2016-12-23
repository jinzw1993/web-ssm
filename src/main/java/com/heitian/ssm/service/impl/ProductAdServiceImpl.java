package com.heitian.ssm.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import com.heitian.ssm.bo.ProductAdBo;
import com.heitian.ssm.dao.ProductDao;
import com.heitian.ssm.util.ResultResolver;
import org.springframework.jca.cci.core.InteractionCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.heitian.ssm.bo.Result;
import com.heitian.ssm.dao.ProductAdDao;
import com.heitian.ssm.model.ProductAd;
import com.heitian.ssm.service.ProductAdService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductAdServiceImpl implements ProductAdService {

    @Resource
    private ProductAdDao productAdDao;
    @Resource
    private ProductDao productDao;

    /**
     * 得到已审核的商品广告列表及其photoURL
     *
     * @return
     */
    public List<ProductAdBo> getVerifiedProductAd() {
        List<ProductAdBo> productAdBos = productAdDao.getPermittedProductAd();
        if (productAdBos != null) {
            getPhotoURL(productAdBos);
        } else {
            productAdBos = new ArrayList<>();
        }
        return productAdBos;
    }

    /**
     * 得到已审核ProductAd数量
     *
     * @return
     */
    public Result getVerifiedPAdNum() {
        Result result = new Result();
        result.setMessage(Integer.toString(productAdDao.getPermittedPAdNum()));
        result.setStatus(1);
        return result;
    }

    /**
     * 更新pAd.status 0--unverified 1--verified 2--rejected 3--out of date
     * 若已过审广告数等于10，则不能再将广告过审
     * @param id
     * @param status
     * @return
     */
    public Result updateProductAdStatus(Long id, Long status) {
        if(status==1) {
            int num= productAdDao.getPermittedPAdNum();
            if(num==10)
                return ResultResolver.returnRes(0);
        }
        int i = productAdDao.updateProductAdStatus(id, status);
        return ResultResolver.returnRes(i);
    }

    /**
     * 添加pAd, 如果该productAd已通过或待审核，则失败
     *
     * @param productId
     * @return
     */
    public Result addProductAd(Long productId, Long price) {
        ProductAd productAd= productAdDao.getProductAdByProductId(productId);
        if(productAd!=null) {
            Result result=new Result();
            result.setMessage("failed, advertisement exists");
            result.setStatus(0);
            return result;
        }
        int i = productAdDao.addProductAd(productId, price);
        return ResultResolver.returnRes(i);
    }

    /**
     * 分页得到所有待审核广告
     * @param page
     * @param pageNum
     * @return
     */
    public List<ProductAdBo> getUnverifiedProductAd(int page, int pageNum) {
        List<ProductAdBo> productAdBos = productAdDao.getUnverifiedProductAd((page - 1) * pageNum, pageNum);
        if (productAdBos != null) {
            getPhotoURL(productAdBos);
        } else {
            productAdBos = new ArrayList<>();
        }
        return productAdBos;
    }

    /**
     * 得到待审核广告的数量
     * @return
     */
    public Result getUnverifiedPAdNum() {
        Result result = new Result();
        result.setMessage(Integer.toString(productAdDao.getUnverifiedPAdNum()));
        result.setStatus(1);
        return result;
    }

    /**
     * 分页根据ownerId得到owner的广告列表
     * @param ownerId
     * @param page
     * @param pageNum
     * @return
     */
    public List<ProductAdBo> getProductAdByOwnerId(Long ownerId, int page, int pageNum) {
        List<ProductAdBo> productAdBos = productAdDao.getProductAdByOwnerId(ownerId, (page - 1) * pageNum, pageNum);
        if (productAdBos != null) {
            getPhotoURL(productAdBos);
        } else {
            productAdBos = new ArrayList<>();
        }
        return productAdBos;
    }

    /**
     * 根据ownerId得到owner的广告数量
     * @param ownerId
     * @return
     */
    public Result getProductAdNumByOwnerId(Long ownerId) {
        Result result = new Result();
        result.setMessage(Integer.toString(productAdDao.getProductAdNumByOwnerId(ownerId)));
        result.setStatus(1);
        return result;
    }

    /**
     * 删除广告
     * @param id ad.id
     * @return
     */
    public Result deleteProductAd(Long id) {
        int i = productAdDao.deleteProductAd(id);
        return ResultResolver.returnRes(i);
    }

    private void getPhotoURL(List<ProductAdBo> productAdBos) {
        for (int i = 0; i < productAdBos.size(); i++) {
            ProductAdBo productAdBo = productAdBos.get(i);
            productAdBo.setPhotoURL(productDao.searchPhotoURL(productAdBo.getProductId()));
        }
    }

/*
    @Override
    public List<ProductAd> showProductAd() {

        List<ProductAd> product = productAdDao.showProductAd();
        if (product == null || product.size() == 0) {
            return null;
        }
        return product;
    }

    @Override


    @Override
    public List<ProductAd> applyProductAd() {
        List<ProductAd> product = productAdDao.applyProductAd();
        if (product == null || product.size() == 0) {
            return null;
        }

        return product;
    }

    @Override
    public Result addProductAd(Long proId, Date date) {
        int i = productAdDao.addProductAd(proId, date);
        return returnRes(i);
    }


    @Override
    public Result rejectProductAd(Long proId) {
        int i = productAdDao.rejectProductAd(proId);
        return returnRes(i);
    }

    @Override
    public Result agreeProductAd(Long proId) {
        int i = productAdDao.agreeProductAd(proId);
        return returnRes(i);
    }
*/
}
