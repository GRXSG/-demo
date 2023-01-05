package top.wennyy.service.serviceIml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.wennyy.entity.SetmealDish;
import top.wennyy.mapper.SetmealDishMapper;
import top.wennyy.service.SetmealDishService;

/**
 * @Author HanWen
 * @Date 2022/12/12 12:08
 * @Version 1.0
 */
@Service
@Slf4j
public class SetmealDishSrvicelmpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {
}
