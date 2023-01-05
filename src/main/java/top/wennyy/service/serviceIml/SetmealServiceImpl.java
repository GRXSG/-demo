package top.wennyy.service.serviceIml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wennyy.common.CustomException;
import top.wennyy.dto.SetmealDto;
import top.wennyy.entity.Setmeal;
import top.wennyy.entity.SetmealDish;
import top.wennyy.mapper.SetmealMapper;
import top.wennyy.service.SetmealDishService;
import top.wennyy.service.SetmealService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author HanWen
 * @Date 2022/11/28 14:46
 * @Version 1.0
 */
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    @Override
    @Transactional//事务同步，要么全部数据插入成功，要么失败
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐的基本信息，操作setmeal，执行insert操作
        this.save(setmealDto);
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        //保存套餐和菜品的关联信息，操作setmeal dish,执行insert操作
        setmealDishService.saveBatch(setmealDishes);



    }

  /**
   * 删除套餐，同时需要删除套餐和菜品的关系
   *
   * @param ids
   */
  @Override
  @Transactional
  public void removeWithDish(List<Long> ids) {
    // 查询套餐状态，确定是否可用删除
      LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
      queryWrapper.in(Setmeal::getId,ids);
      queryWrapper.eq(Setmeal::getStatus,1);
      int count = this.count(queryWrapper);
      if (count>0){
          // 如果不能删除，抛出一个业务异崩
          throw new CustomException("套餐正在售卖中，不能删除");
      }
    // 如果可以删除，先删除套餐表中的数据--setmeal
        this.removeByIds(ids);
    // 删除关系表中的数据--setmeal_dish
      LambdaQueryWrapper<SetmealDish> queryWrapper1 = new LambdaQueryWrapper<>();
      queryWrapper1.in(SetmealDish::getSetmealId,ids);
      setmealDishService.remove(queryWrapper1);
  }
}
