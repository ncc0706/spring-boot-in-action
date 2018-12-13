package io.arukas;

import io.arukas.dto.ResponseEntity;
import io.arukas.service.IJobAndTriggerService;
import io.arukas.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/12/5 23:13 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */
@RestController
public class TaskEndpoint {


    @Autowired
    private IJobAndTriggerService jobAndTriggerService;

    @GetMapping(value = "tasks")
    public Map<String, Object> queryjob(PageUtil search, HttpServletRequest request, HttpServletResponse response) {
        return jobAndTriggerService.getPageJob(search);
    }

    @GetMapping("tasks/add")
    public ResponseEntity addJob(
            @RequestParam(value = "jobName") String jobName,
            @RequestParam(value = "jobClass") String jobClass,
            @RequestParam(value = "jobGroupName") String jobGroupName,
            @RequestParam(value = "cronExpression") String cronExpression,
            HttpServletResponse response) {
        try {
            jobAndTriggerService.addJob(jobName, jobClass, jobGroupName, cronExpression);
            return ResponseEntity.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.FAILURE(e.getMessage());
        }
    }

    /**
     * @param jobClassName 类名
     * @param jobGroupName 组名
     * @Title: pauseJob
     * @Description: (暂停Job)
     */
    @GetMapping(value = "tasks/pause")
    public ResponseEntity pauseJob(
            @RequestParam(value = "jobClassName") String jobClassName,
            @RequestParam(value = "jobGroupName") String jobGroupName,
            HttpServletResponse response) {
        try {
            jobAndTriggerService.pauseJob(jobClassName, jobGroupName);
            return ResponseEntity.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.FAILURE(e.getMessage());
        }
    }
}
