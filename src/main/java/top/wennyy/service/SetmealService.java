package top.wennyy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.wennyy.dto.SetmealDto;
import top.wennyy.entity.Setmeal;

import java.util.List;

/**
 * @Author HanWen
 * @Date 2022/11/28 14:41
 * @Version 1.0
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关系
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
