package com.yunquanlai.admin.delivery.controller;

import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.delivery.service.DeliveryDistributorService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.validator.Assert;
import com.yunquanlai.utils.validator.ValidatorUtils;
import com.yunquanlai.utils.validator.group.AddGroup;
import com.yunquanlai.utils.validator.group.UpdateGroup;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;




/**
 * 配送员信息
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 08:25:11
 */
@RestController
@RequestMapping("deliverydistributor")
public class DeliveryDistributorController extends AbstractController {
	@Autowired
	private DeliveryDistributorService deliveryDistributorService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("deliverydistributor:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DeliveryDistributorEntity> deliveryDistributorList = deliveryDistributorService.queryList(query);
		int total = deliveryDistributorService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(deliveryDistributorList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("deliverydistributor:info")
	public R info(@PathVariable("id") Long id){
		DeliveryDistributorEntity deliveryDistributor = deliveryDistributorService.queryObject(id);
		
		return R.ok().put("deliveryDistributor", deliveryDistributor);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("deliverydistributor:save")
	public R save(@RequestBody DeliveryDistributorEntity deliveryDistributor){
	    //校验配送员信息
        ValidatorUtils.validateEntity(deliveryDistributor, AddGroup.class);
        Assert.isNotNull(deliveryDistributorService.queryObjectByPhone(deliveryDistributor.getPhone()),"新增配送员手机号重复。");

		if (deliveryDistributor != null){
			//status状态默认为20
			deliveryDistributor.setStatus(20);
			//新创建停用状态，默认值为2
			deliveryDistributor.setDisable( 2);
			String pwd = deliveryDistributor.getPassword();
			//初始密码：123456
			if(StringUtils.isEmpty(pwd)){
				pwd = "123456";
			}
            //对密码进行加密
            deliveryDistributor.setPassword(DigestUtils.sha256Hex(pwd));
			deliveryDistributor.setAmount(BigDecimal.ZERO);
			deliveryDistributor.setOrderCount(0);
			deliveryDistributorService.save(deliveryDistributor);
			return R.ok();
		} else {
			return R.error();
		}
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("deliverydistributor:update")
	public R update(@RequestBody DeliveryDistributorEntity deliveryDistributor){
        //校验配送员信息
        ValidatorUtils.validateEntity(deliveryDistributor, UpdateGroup.class);

		deliveryDistributorService.update(deliveryDistributor);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("deliverydistributor:delete")
	public R delete(@RequestBody Long[] ids){
		deliveryDistributorService.deleteBatch(ids);
		
		return R.ok();
	}

	/**
	 * 根据配送点id获取配送员
	 * @return
	 */
	@RequestMapping("/select")
	@RequiresPermissions("deliverydistributor:list")
	public R listByDeliveryEndpointId(Long deliveryEndpointId){

		List<DeliveryDistributorEntity> deliveryDistributorEntities = deliveryDistributorService.queryListByDeliveryEndpointId(deliveryEndpointId);
		return R.ok().put("deliveryDistributorEntities", deliveryDistributorEntities);
	}
}
