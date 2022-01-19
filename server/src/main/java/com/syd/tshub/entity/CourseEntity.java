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
 * CourseEntity: 数据映射实体定义
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
    table = "t_course",
    schema = "test_hub"
)
public class CourseEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  @TableId("id")
  private Integer id;

  @TableField(
      value = "class_room",
      desc = "教室名称"
  )
  private String classRoom;

  @TableField(
      value = "count",
      desc = "课程节数"
  )
  private Integer count;

  @TableField(
      value = "course_name",
      desc = "课程名称"
  )
  private String courseName;

  @TableField("create_time")
  private Date createTime;

  @TableField(
      value = "enable",
      desc = "是否有效"
  )
  private Integer enable;

  @TableField(
      value = "teacher_id",
      desc = "教师id"
  )
  private Integer teacherId;

  @TableField(
      value = "teacher_name",
      desc = "教师名称"
  )
  private String teacherName;

  @TableField("update_time")
  private Date updateTime;

  @Override
  public final Class entityClass() {
    return CourseEntity.class;
  }
}
