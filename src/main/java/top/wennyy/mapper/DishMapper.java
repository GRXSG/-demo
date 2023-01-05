package top.wennyy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.wennyy.entity.Dish;

/**
 * @Author HanWen
 * @Date 2022/11/28 14:27
 * @Version 1.0
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
