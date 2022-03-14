package com.syd.tshub.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * UserLogEntity: 数据映射实体定义
 *
 * @author Powered By Fluent Mybatis
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Data
@Accessors(
    chain = true
)
@EqualsAndHashCode(
    callSuper = false
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FluentMybatis(
    table = "t_user_log",
    schema = "test_hub"
)
public class UserLogEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  @TableId("id")
  private Integer id;

  @TableField("content")
  private String content;

  @TableField("create_time")
  private Date createTime;

  @TableField("user_id")
  private Integer userId;

  @TableField("username")
  private String username;

  @Override
  public final Class entityClass() {
    return UserLogEntity.class;
  }
}
