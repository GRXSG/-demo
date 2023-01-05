package top.wennyy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.wennyy.entity.User;

/**
 * @Author HanWen
 * @Date 2022/12/12 20:36
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
