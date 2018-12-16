package io.arukas.service;

import io.arukas.entity.TaskInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/12/5 23:33 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */
public interface TaskInfoService {

    Page<TaskInfo> taskList(Pageable pageable);

    void addJob(String jobName, String jobClass, String jobGroupName, String cronExpression) throws Exception;

    void updateJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception;

    void deleteJob(String jobClassName, String jobGroupName) throws Exception;

    void pauseJob(String jobClassName, String jobGroupName) throws Exception;

    void resumejob(String jobClassName, String jobGroupName) throws Exception;

}
