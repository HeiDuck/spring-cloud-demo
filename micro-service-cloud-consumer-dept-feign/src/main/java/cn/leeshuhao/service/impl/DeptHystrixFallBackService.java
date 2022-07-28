package cn.leeshuhao.service.impl;

import cn.leeshuhao.service.DeptHystrixService;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author MrLee
 */
@Service
public class DeptHystrixFallBackService implements DeptHystrixService {
    @Override
    public String deptInfoOk(Integer id) {
        return "--------------------leeshuhao提醒您，系统繁忙，请稍后重试！（解耦回退方法触发）-----------------------";
    }

    @Override
    public String deptInfoTimeout(Integer id) {
        return "--------------------leeshuhao提醒您，系统繁忙，请稍后重试！（解耦回退方法触发）-----------------------";
    }
}
