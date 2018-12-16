package io.arukas.endpoint;

import com.alibaba.fastjson.JSON;
import io.arukas.entity.TaskInfo;
import io.arukas.service.TaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/12/5 23:13 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */
@Controller
public class TaskEndpoint {

    @Autowired
    private TaskInfoService taskInfoService;

    @GetMapping(value = "list-task")
    public String taskList(Pageable pageable, ModelMap modelMap) {
        Page<TaskInfo> taskList = taskInfoService.taskList(pageable);
        modelMap.put("taskList", taskList);
        return "tasks";
    }

    @ResponseBody
    @GetMapping(value = "tasks")
    public Page<TaskInfo> taskList(Pageable pageable) {
        return taskInfoService.taskList(pageable);
    }

    @GetMapping(value = "add-task")
    public String createTask() {

        return "addTask";
    }

    @ResponseBody
    @PostMapping(value = "add-task")
    public void createTask(TaskInfo taskInfo) throws Exception {
        taskInfoService.addJob(taskInfo);
    }

}
