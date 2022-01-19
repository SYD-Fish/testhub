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
 * ScheduleEntity: 数据映射实体定义
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
    table = "t_schedule",
    schema = "test_hub"
)
public class ScheduleEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  @TableId("id")
  private Integer id;

  @TableField("course_id")
  private Integer courseId;

  @TableField(
      value = "course_name",
      desc = "课程名称"
  )
  private String courseName;

  @TableField("enable")
  private Integer enable;

  @TableField(
      value = "end_date",
      desc = "结束日期"
  )
  private Date endDate;

  @TableField(
      value = "start_date",
      desc = "开始日期"
  )
  private Date startDate;

  @TableField("time_period")
  private Integer timePeriod;

  @Override
  public final Class entityClass() {
    return ScheduleEntity.class;
  }
}
