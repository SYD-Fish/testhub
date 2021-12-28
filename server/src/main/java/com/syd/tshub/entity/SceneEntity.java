package com.syd.tshub.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * SceneEntity: 数据映射实体定义
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
@FluentMybatis(
    table = "t_scene",
    schema = "test_hub"
)
public class SceneEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   */
  @TableId("id")
  private Integer id;

  /**
   */
  @TableField("create_time")
  private Date createTime;

  /**
   */
  @TableField("project_id")
  private String projectId;

  /**
   */
  @TableField("scene_name")
  private String sceneName;

  /**
   */
  @TableField("update_time")
  private Date updateTime;

  @Override
  public final Class entityClass() {
    return SceneEntity.class;
  }
}