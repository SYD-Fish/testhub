package com.syd.tshub.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
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
  @ExcelIgnore
  private Integer id;

  @TableField(
      value = "course_id",
      desc = "课程id"
  )
  @ExcelIgnore
  private Integer courseId;

  @TableField(
      value = "course_name",
      desc = "课程名称"
  )
  @ExcelProperty(value={"课程名称"}, order = 2)
  private String courseName;

  @TableField("create_time")
  @ExcelProperty(value={"时间"}, order = 4)
  @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  @TableField("enbal")
  @ExcelIgnore
  private Integer enbal;

  @TableField(
      value = "grade",
      desc = "成绩"
  )
  @ExcelProperty(value={"成绩"}, order = 3)
  private Integer grade;

  @TableField("update_time")
  @ExcelIgnore
  private Date updateTime;

  @TableField(
      value = "user_id",
      desc = "学生id"
  )
  @ExcelIgnore
  private Integer userId;

  @TableField(
      value = "user_name",
      desc = "学生名称"
  )
  @ExcelProperty(value={"学生名称"}, order = 1)
  private String userName;

  @Override
  public final Class entityClass() {
    return GradeEntity.class;
  }
}
