package com.yunquanlai.api;

import com.yunquanlai.admin.product.service.ProductInfoService;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.annotation.IgnoreAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  weicc
 * @date  2018/5/30 21:15
 * @desc
 **/
@RestController
@RequestMapping("/api")
@Api("商品接口")
public class ApiProductController {
    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 商品查询
     */
    @IgnoreAuth
    @PostMapping("trackProduct")
    @ApiOperation(value = "商品查询")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", dataType="string", name = "name", value = "商品名称", required = true),
        @ApiImplicitParam(paramType = "query", dataType="double", name = "amount", value = "实价", required = true),
        @ApiImplicitParam(paramType = "query", dataType="string", name = "brand_name", value = "商品品牌", required = true),
        @ApiImplicitParam(paramType = "query", dataType="int", name = "bucket_type", value = "桶类型", required = true)
    })
    public R trackProduct(String name, double amount, String brand_name, int bucket_type){
        return R.ok();

    }
}
