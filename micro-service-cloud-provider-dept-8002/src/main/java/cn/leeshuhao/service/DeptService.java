package cn.leeshuhao.service;

import cn.leeshuhao.entity.Dept;

import java.util.List;

/**
 * <p></p>
 *
 * @author MrLee
 */
public interface DeptService {
    Dept get(Integer deptNo);
    List<Dept> selectAll();
}
