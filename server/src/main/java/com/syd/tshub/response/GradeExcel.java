package com.syd.tshub.response;

import cn.org.atool.fluent.mybatis.annotation.TableField;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.Date;

/**
 * 类描述：
 *
 * @ClassName GradeExcel
 * @Description TODO
 * @Author syd
 * @Date 2022/1/19 20:20
 * @Version 1.0
 */
@Data
public class GradeExcel {

    @ExcelProperty(value={"课程名称"}, order = 2)
    private String courseName;

    @ExcelProperty(value={"时间"}, order = 4)
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ExcelProperty(value={"成绩"}, order = 3)
    private Integer grade;

    @ExcelProperty(value={"学生名称"}, order = 1)
    private String userName;

}
