package com.yunquanlai.admin.comment.dao;

import com.yunquanlai.admin.comment.entity.CommentProductEntity;
import com.yunquanlai.admin.system.dao.BaseDao;

import java.util.List;

/**
 * 配送员评价
 * 
 * @author weicc
 * @email 
 * @date 2018-06-10 16:28:26
 */
public interface CommentProductDao extends BaseDao<CommentProductEntity> {
    List<CommentProductEntity> queryCommentProductList(Long productId);
}
