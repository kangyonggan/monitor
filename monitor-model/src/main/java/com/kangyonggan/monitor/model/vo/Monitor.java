package com.kangyonggan.monitor.model.vo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "monitor")
public class Monitor implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 系统名称
     */
    private String app;

    /**
     * 监控类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;

    /**
     * 包名
     */
    @Column(name = "package_name")
    private String packageName;

    /**
     * 类名
     */
    @Column(name = "class_name")
    private String className;

    /**
     * 方法名
     */
    @Column(name = "method_name")
    private String methodName;

    /**
     * 开始时间
     */
    @Column(name = "begin_time")
    private Long beginTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Long endTime;

    /**
     * 逻辑删除
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 返回值类型
     */
    @Column(name = "return_type")
    private String returnType;

    /**
     * 参数类型
     */
    private String argTypes;

    private static final long serialVersionUID = 1L;
}