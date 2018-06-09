package com.yunquanlai.api.comsumer;

import com.yunquanlai.admin.product.entity.ProductBrandEntity;
import com.yunquanlai.admin.product.entity.ProductInfoEntity;
import com.yunquanlai.admin.product.entity.ProductInfoVO;
import com.yunquanlai.admin.product.service.ProductBrandService;
import com.yunquanlai.admin.product.service.ProductInfoService;
import com.yunquanlai.admin.system.service.SysConfigService;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.annotation.IgnoreAuth;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weicc
 * @date 2018/5/30 21:15
 * @desc
 **/
@RestController
@RequestMapping("/client/api")
@Api(value = "客户端-产品", description = "产品相关接口")
public class ApiProductController {
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductBrandService productBrandService;

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 获取商品首页轮播图
     *
     * @return
     */
    @IgnoreAuth
    @PostMapping("getProductBanner")
    @ApiOperation(value = "商品轮播图信息")
    public R getProductBanner() {
        String banner = sysConfigService.getValue("banner", "");
        return R.ok().put("banner", banner);
    }

    /**
     * 获取商品品牌信息
     *
     * @return
     */
    @IgnoreAuth
    @PostMapping("getProductBrand")
    @ApiOperation(value = "商品品牌信息")
    public R getProductBrand() {
        List<ProductBrandEntity> productBrandEntityList = productBrandService.queryAll();
        return R.ok().put("productBrandEntityList", productBrandEntityList);
    }

    /**
     * 获取商品详细信息
     *
     * @return
     */
    @IgnoreAuth
    @PostMapping("getProductDetail")
    @ApiOperation(value = "获取商品详细信息")
    @ApiImplicitParam(paramType = "query", dataType = "long", name = "id", value = "商品 ID", required = true)
    public R getProductDetail(@RequestParam Long id) {
        ProductInfoVO productInfoVO = productInfoService.queryProductInfoVO(id);
        return R.ok().put("productInfoVO", productInfoVO);
    }

    /**
     * 商品查询
     */
    @IgnoreAuth
    @PostMapping("queryProduct")
    @ApiOperation(value = "商品查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "name", value = "商品名称"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "brandId", value = "商品品牌(传ID)"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "orderType", value = "排序类型，10：按价格升序，20：按价格降序"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "bucketType", value = "规格，10：一次性桶装水，20：循环桶装水，30：瓶装谁，40：全部"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "isQuick", value = "是否一键送水,是：10，否：20", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "offset", value = "位移数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "limit", value = "查询条数", required = true)
    })
    public R queryProduct(String name, Integer brandId, Integer orderType, Integer bucketType, Integer isQuick, @RequestParam Integer offset, @RequestParam Integer limit) {
        Map map = new HashMap(16);
        map.put("name", name);
        map.put("brandId", brandId);
        if (isQuick == 10) {
            map.put("isQuick", isQuick);
        }

        if (orderType != null) {
            map.put("sidx", "amount_show");
            if (orderType == 10) {
                map.put("order", "desc");
            } else if (orderType == 20) {
                map.put("order", "asc");
            }
        }

        map.put("bucketType", bucketType);
        map.put("offset", offset);
        map.put("limit", limit);
        List<ProductInfoEntity> productInfoEntities = productInfoService.queryListForClient(map);
        return R.ok().put("productInfoList", productInfoEntities);

    }
}
