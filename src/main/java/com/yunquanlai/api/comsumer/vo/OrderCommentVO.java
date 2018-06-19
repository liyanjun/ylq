package com.yunquanlai.api.comsumer.vo;

import com.yunquanlai.admin.comment.controller.CommentProductController;
import com.yunquanlai.admin.comment.entity.CommentDeliveryEntity;
import com.yunquanlai.admin.comment.entity.CommentProductEntity;
import com.yunquanlai.admin.comment.service.CommentDeliveryService;

import java.math.BigDecimal;
import java.util.List;

/**
 * 下单信息
 */
public class OrderCommentVO {

    private Long orderId;

    private CommentDeliveryEntity commentDeliveryEntity;

    private List<CommentProductEntity> commentProductEntities;

    public CommentDeliveryEntity getCommentDeliveryEntity() {
        return commentDeliveryEntity;
    }

    public void setCommentDeliveryEntity(CommentDeliveryEntity commentDeliveryEntity) {
        this.commentDeliveryEntity = commentDeliveryEntity;
    }

    public List<CommentProductEntity> getCommentProductEntities() {
        return commentProductEntities;
    }

    public void setCommentProductEntities(List<CommentProductEntity> commentProductEntities) {
        this.commentProductEntities = commentProductEntities;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
