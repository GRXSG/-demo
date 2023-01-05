package top.wennyy.service.serviceIml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.wennyy.entity.Employee;
import top.wennyy.mapper.EmployeeMapper;
import top.wennyy.service.EmployeeService;

/**
 * @Author HanWen
 * @Date 2022/11/16 10:49
 * @Version 1.0
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
