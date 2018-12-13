package io.arukas.service;

import io.arukas.dto.JobAndTriggerDto;
import io.arukas.utils.PageUtil;

import java.util.Map;

/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/12/5 23:33 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */
public interface IJobAndTriggerService {

    /**
     * @param @param  search
     * @param @return 参数
     * @return Map<String   ,   Object>    返回类型
     * @throws
     * @Title: getPageJob
     * @Description: TODO(查询定时任务 ， 分页)
     */
    Map<String, Object> getPageJob(PageUtil search);

    /**
     * @param @return 参数
     * @return JobAndTriggerDto    返回类型
     * @throws
     * @Title: getPageJobmod
     * @Description: TODO(查询定时任务)
     */
    JobAndTriggerDto getPageJobmod();

    /**
     * @param @param  jobClassName 任务路径名称
     * @param @param  jobGroupName 任务分组
     * @param @param  cronExpression cron时间规则
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
     * @Title: addJob
     * @Description: TODO(添加任务)
     */
    void addJob(String jobName, String jobClass, String jobGroupName, String cronExpression) throws Exception;

    /**
     * @param @param  jobClassName 任务路径名称
     * @param @param  jobGroupName 任务分组
     * @param @param  cronExpression cron时间规则
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
     * @Title: updateJob
     * @Description: TODO(更新定时任务)
     */
    void updateJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception;

    /**
     * @param @param  jobClassName 任务路径名称
     * @param @param  jobGroupName 任务分组
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
     * @Title: deleteJob
     * @Description: TODO(删除定时任务)
     */
    void deleteJob(String jobClassName, String jobGroupName) throws Exception;

    /**
     * @param @param  jobClassName 任务路径名称
     * @param @param  jobGroupName 任务分组
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
     * @Title: pauseJob
     * @Description: TODO(暂停定时任务)
     */
    void pauseJob(String jobClassName, String jobGroupName) throws Exception;

    /**
     * @param @param  jobClassName 任务路径名称
     * @param @param  jobGroupName 任务分组
     * @param @throws Exception    参数
     * @return void    返回类型
     * @throws
     * @Title: resumejob
     * @Description: TODO(恢复任务)
     */
    void resumejob(String jobClassName, String jobGroupName) throws Exception;

}
