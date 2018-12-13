package io.arukas.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by IntelliJ IDEA. <br/>
 *
 * @Auther: 牛玉贤 <br/>
 * @Date: 2018/12/5 23:42 <br/>
 * @Email: ncc0706@gmail.com <br/>
 */
public class HelloJob implements BaseJob {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.err.println("Hello Job执行时间: " + new Date());
    }
}
