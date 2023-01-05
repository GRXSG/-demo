package top.wennyy.service.serviceIml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.wennyy.entity.User;
import top.wennyy.mapper.UserMapper;
import top.wennyy.service.UserService;

/**
 * @Author HanWen
 * @Date 2022/12/12 20:38
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
