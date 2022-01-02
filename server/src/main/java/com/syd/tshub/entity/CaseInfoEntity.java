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
 * CaseInfoEntity: 数据映射实体定义
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
    table = "t_case_info",
    schema = "test_hub"
)
public class CaseInfoEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  @TableId("id")
  private Integer id;

  @TableField("create_time")
  private Date createTime;

  @TableField("enable")
  private Integer enable;

  @TableField("method")
  private String method;

  @TableField("project_id")
  private Integer projectId;

  @TableField("request_body")
  private String requestBody;

  @TableField("request_header")
  private String requestHeader;

  @TableField("update_time")
  private Date updateTime;

  @TableField("url")
  private String url;

  @Override
  public final Class entityClass() {
    return CaseInfoEntity.class;
  }
}
