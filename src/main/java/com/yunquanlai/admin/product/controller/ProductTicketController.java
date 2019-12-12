package com.yunquanlai.admin.product.controller;

import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.admin.product.entity.ProductTicketEntity;
import com.yunquanlai.admin.product.service.ProductTicketService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.RRException;
import com.yunquanlai.utils.validator.ValidatorUtils;
import com.yunquanlai.utils.validator.group.AddGroup;
import com.yunquanlai.utils.validator.group.UpdateGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 商品水票信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-07-17 12:11:07
 */
@RestController
@RequestMapping("productticket")
public class ProductTicketController extends AbstractController {
	@Autowired
	private ProductTicketService productTicketService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("productticket:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ProductTicketEntity> productTicketList = productTicketService.queryList(query);
		int total = productTicketService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(productTicketList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("productticket:info")
	public R info(@PathVariable("id") Long id){
		ProductTicketEntity productTicket = productTicketService.queryObject(id);
		
		return R.ok().put("productTicket", productTicket);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("productticket:save")
	public R save(@RequestBody ProductTicketEntity productTicket){
		//校验水票信息
		ValidatorUtils.validateEntity(productTicket, AddGroup.class);
        //检验水票编号唯一性
        ProductTicketEntity productTicketEntity = productTicketService.queryObjectByProductTicketNum(productTicket.getProductTicketNum());
        if(productTicketEntity != null){
            throw new RRException("水票编号已存在 ！");
        }
		productTicket.setCout(productTicket.getPurchaseCount()+productTicket.getGiftCount());
		productTicket.setCreationTime(new Date());
		productTicketService.save(productTicket);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("productticket:update")
	public R update(@RequestBody ProductTicketEntity productTicket){
        //校验水票信息
        ValidatorUtils.validateEntity(productTicket, UpdateGroup.class);
		productTicketService.update(productTicket);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("productticket:delete")
	public R delete(@RequestBody Long[] ids){
		productTicketService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
