package com.yunquanlai.api;

import com.yunquanlai.utils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  weicc
 * @date  2018/5/30 22:05
 * @desc
 **/
@RestController
@RequestMapping("/api")
@Api("配送接口")
public class ApiDeliveryController {

    public R recyclingEmptyBarrels(){
        return R.ok();
    }
}
