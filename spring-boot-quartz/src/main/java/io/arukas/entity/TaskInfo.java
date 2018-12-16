package io.arukas.entity;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.quartz.JobDataMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/12/16 15:08 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */
@Data
@Entity
@Immutable
@Table(name = "task_info_view")
public class TaskInfo {

    @Id
    private String id;

    // 任务名称
    private String jobName;

    //任务分组
    private String jobGroup;

    // 任务执行Class
    private Class jobClass;

    //任务描述
    private String jobDescription;

    //任务状态
//    WAITING:等待
//    PAUSED:暂停
//    ACQUIRED:正常执行
//    BLOCKED：阻塞
//    ERROR：错误
    private String state;

    //任务表达式
    private String cronExpression;

    private String prevFireTime;

    private String nextFireTime;

    // 创建日期
    private String startTime;

    // 触发器名称
    private String triggerName;

    // 触发器分组名称
    private String triggerGroup;

    @Transient
    private JobDataMap jobDataMap;
}
