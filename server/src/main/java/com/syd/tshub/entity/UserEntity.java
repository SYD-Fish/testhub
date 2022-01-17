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
 * UserEntity: 数据映射实体定义
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
    table = "t_user",
    schema = "test_hub"
)
public class UserEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  @TableId("user_id")
  private Integer userId;

  @TableField("create_time")
  private Date createTime;

  @TableField("email")
  private String email;

  @TableField("enable")
  private Integer enable;

  @TableField("login_status")
  private Integer loginStatus;

  @TableField("password")
  private String password;

  @TableField("phone")
  private String phone;

  @TableField("profession")
  private String profession;

  @TableField(
      value = "role_id",
      desc = "角色Id"
  )
  private Integer roleId;

  @TableField("stage")
  private String stage;

  @TableField("update_time")
  private Date updateTime;

  @TableField("user_name")
  private String userName;

  @Override
  public final Class entityClass() {
    return UserEntity.class;
  }
}
