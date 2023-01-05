package top.wennyy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.wennyy.dto.DishDto;
import top.wennyy.entity.Dish;

/** @Author HanWen @Date 2022/11/28 14:41 @Version 1.0 */
public interface DishService extends IService<Dish> {
  // 新增菜品，同时插入菜品对应的口味数据，
  // 需要操作两张表：dish、dishflavor
    public void saveWithFlavor(DishDto dishDto);
//根據id查詢菜品信息和對應的口味表
    public DishDto getByIdWithFlavor(Long id);

    //更新菜品信息，同时更新对应的口味信息
    public void updateWithFlavor(DishDto dishDto);
}
