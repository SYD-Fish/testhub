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
 * MenuEntity: 数据映射实体定义
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
    table = "t_menu",
    schema = "test_hub"
)
public class MenuEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  @TableId("menu_id")
  private Integer menuId;

  @TableField("create_time")
  private Date createTime;

  @TableField("enable")
  private Integer enable;

  @TableField("icon")
  private String icon;

  @TableField("index")
  private Integer index;

  @TableField("menu_name")
  private String menuName;

  @TableField("parent_id")
  private Integer parentId;

  @TableField("path")
  private String path;

  @TableField("update_time")
  private Date updateTime;

  @Override
  public final Class entityClass() {
    return MenuEntity.class;
  }
}
