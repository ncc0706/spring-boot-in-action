package io.arukas.service;

import io.arukas.entity.TaskInfo;
import io.arukas.repository.TaskInfoRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/12/5 23:37 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */
@Service
public class TaskInfoImpl implements TaskInfoService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private TaskInfoRepository taskInfoRepository;


    @Transactional(readOnly = true)
    public Page<TaskInfo> taskList(Pageable pageable) {
        return taskInfoRepository.findAll(pageable);
    }


    @Override
    public void addJob(TaskInfo taskInfo) throws Exception{

        String jobName = taskInfo.getJobName();
        String jobGroup = taskInfo.getJobGroup();

        String jobDescription = taskInfo.getJobDescription();
        String cronExpression = taskInfo.getCronExpression();
        Class jobClass = taskInfo.getJobClass();

        JobDataMap jobDataMap = taskInfo.getJobDataMap();

        // 构建job信息
        final JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(jobKey)
                .withDescription(jobDescription)
                .build();

        // 表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
                .withMisfireHandlingInstructionDoNothing();

        // 按新的cronExpression表达式构建一个新的trigger
        final TriggerKey triggerKey = TriggerKey.triggerKey(jobName + "(trigger)", jobGroup);
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .withDescription(jobDescription)
                .withSchedule(scheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public void updateJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败" + e);
            throw new Exception("更新定时任务失败");
        }
    }

    @Override
    public void deleteJob(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    @Override
    public void pauseJob(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    @Override
    public void resumejob(String jobClassName, String jobGroupName) throws Exception {
        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

//    public static BaseJob getClass(String classname) throws Exception {
//        Class<?> class1 = Class.forName(classname);
//        return (BaseJob) class1.newInstance();
//    }

}
