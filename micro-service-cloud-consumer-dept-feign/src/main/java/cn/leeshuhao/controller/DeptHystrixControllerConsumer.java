package cn.leeshuhao.controller;

import cn.leeshuhao.service.DeptHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @author MrLee
 */
@Slf4j
@RestController
@DefaultProperties(defaultFallback = "deptGlobalFallbackMethod")
public class DeptHystrixControllerConsumer {

    @Resource
    private DeptHystrixService deptHystrixService;

    @RequestMapping(value = "/consumer/dept/hystrix/ok/{id}")
    public String deptInfoOk(@PathVariable("id") Integer id) {
        return deptHystrixService.deptInfoOk(id);
    }

    //在客户端进行降级
    @RequestMapping(value = "/consumer/dept/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "deptTimeoutHandler") //为该请求指定专属的回退方法
    @HystrixCommand() //为该请求指定专属的回退方法
    public String deptInfoTimeout(@PathVariable("id") Integer id) {
        String s = deptHystrixService.deptInfoTimeout(id);
        log.info(s);
        return s;
    }

    // deptInfo_Timeout方法的 专用 fallback 方法
    public String deptTimeoutHandler(@PathVariable("id") Integer id) {
        log.info("deptInfo_Timeout 出错，服务已被降级！");
        return "您的队友提醒您：服务端系统繁忙，请稍后再试！（客户端 deptInfo_Timeout 专属的回退方法触发）";
    }

    /**
     * 全局的 fallback 方法，
     * 回退方法必须和 hystrix 的执行方法在相同类中
     * @DefaultProperties(defaultFallback = "dept_Global_FallbackMethod") 类上注解，请求方法上使用 @HystrixCommand 注解
     */
    public String deptGlobalFallbackMethod() {
        return "队友提醒您，运行出错或服务端系统繁忙，请稍后再试！（客户端全局回退方法触发,）";
    }
}
