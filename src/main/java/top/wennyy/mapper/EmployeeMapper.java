package top.wennyy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.wennyy.entity.Employee;

/**
 * @Author HanWen
 * @Date 2022/11/16 10:47
 * @Version 1.0
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
