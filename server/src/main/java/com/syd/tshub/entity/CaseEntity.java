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
 * CaseEntity: 数据映射实体定义
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
    table = "t_case",
    schema = "test_hub"
)
public class CaseEntity extends RichEntity {
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
  @TableField("enable")
  private Integer enable;

  /**
   */
  @TableField("method")
  private String method;

  /**
   */
  @TableField("project_id")
  private Integer projectId;

  /**
   */
  @TableField("request_body")
  private String requestBody;

  /**
   */
  @TableField("request_header")
  private String requestHeader;

  /**
   */
  @TableField("update_time")
  private Date updateTime;

  /**
   */
  @TableField("url")
  private String url;

  @Override
  public final Class entityClass() {
    return CaseEntity.class;
  }
}
