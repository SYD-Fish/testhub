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
 * GradeEntity: 数据映射实体定义
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
    table = "t_grade",
    schema = "test_hub"
)
public class GradeEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  @TableId("id")
  private Integer id;

  @TableField(
      value = "course_id",
      desc = "课程id"
  )
  private Integer courseId;

  @TableField(
      value = "course_name",
      desc = "课程名称"
  )
  private String courseName;

  @TableField("create_time")
  private Date createTime;

  @TableField("enbal")
  private Integer enbal;

  @TableField(
      value = "grade",
      desc = "成绩"
  )
  private Integer grade;

  @TableField("update_time")
  private Date updateTime;

  @TableField(
      value = "user_id",
      desc = "学生id"
  )
  private Integer userId;

  @TableField(
      value = "username",
      desc = "学生名称"
  )
  private String username;

  @Override
  public final Class entityClass() {
    return GradeEntity.class;
  }
}
