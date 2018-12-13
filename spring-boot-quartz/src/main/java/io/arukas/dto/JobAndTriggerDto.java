package io.arukas.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/12/5 23:36 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */
@Data
public class JobAndTriggerDto {

    private String jobName;
    private String jobDescription;
    private String jobGroupName;
    private String jobClassName;
    private String triggerName;
    private String triggerGroupName;
    private String prevFireTime;
    private String nextFireTime;
    private String cronExpression;
    private String triggerState;
}
