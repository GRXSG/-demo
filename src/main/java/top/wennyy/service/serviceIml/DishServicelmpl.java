package top.wennyy.service.serviceIml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wennyy.dto.DishDto;
import top.wennyy.entity.Dish;
import top.wennyy.entity.DishFlavor;
import top.wennyy.mapper.DishMapper;
import top.wennyy.service.DishFlavorService;
import top.wennyy.service.DishService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author HanWen
 * @Date 2022/11/28 14:43
 * @Version 1.0
 */
@Service
@Slf4j
public class DishServicelmpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;
    /**
     *新增菜品，同时保存对应的口味数据
     * @param dishDto
     */
    @Override
    @Transactional//事务控制，保证事务一致性
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息到菜品表dish
        this.save(dishDto);
        Long dishId = dishDto.getId();//菜品id
        //菜品口味
        List<DishFlavor> flavors =dishDto.getFlavors();
        //为口味赋值id
        flavors=flavors.stream().map((item)->{
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        //保存菜品口味数据到菜品口味表dish flavor
        dishFlavorService.saveBatch(flavors);

    }

    /**
     * //根據id查詢菜品信息和對應的口味表
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //查询菜品基本信息，从dish表查询
        Dish dish  = this.getById(id);
        //把口味拼接进去，把dish的属性复制到dishdto
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);

        //查询当前菜品对应的口味信息，从dish_flavor表查询
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        //根据dish_id进行多表查询,把dishflavor查出来
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        //把查询出来的爱好变成集合
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        //把口味偏好装载进去
        dishDto.setFlavors(flavors);
        return dishDto;
    }

    /**
     *  //更新菜品信息，同时更新对应的口味信息
     * @param dishDto
     */
    @Override
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish表基本信息
        this.updateById(dishDto);
        //清理当前菜品对应口味数据--dish flavor表的deletei操作
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper);
        //添加当前提交过来的口味数据--dish flavor表的insert操作
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors=flavors.stream().map((item)->{
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }
}
