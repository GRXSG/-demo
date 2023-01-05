package top.wennyy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.wennyy.common.R;
import top.wennyy.dto.SetmealDto;
import top.wennyy.entity.Category;
import top.wennyy.entity.Dish;
import top.wennyy.entity.Setmeal;
import top.wennyy.entity.SetmealDish;
import top.wennyy.service.CategoryService;
import top.wennyy.service.SetmealDishService;
import top.wennyy.service.SetmealService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author HanWen
 * @Date 2022/12/12 12:40
 * @Version 1.0
 * 套餐管理
 */
@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private CategoryService categoryService;


    /**
     * 套餐分页管理
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        //分页构造器
        Page<Setmeal> pageinfo = new Page<>(page, pageSize);
        Page<SetmealDto> dtoPage = new Page<>();

        //条件构造器
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(name != null,Setmeal::getName,name);
        //添加排序条件，根据价格进行排序
        queryWrapper.orderByAsc(Setmeal::getPrice);
        //把查询出来的数据进行分页
        setmealService.page(pageinfo,queryWrapper);

        BeanUtils.copyProperties(pageinfo,dtoPage,"records");
        List<Setmeal> records = pageinfo.getRecords();
        List<SetmealDto> list=records.stream().map((item)->{
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item,setmealDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null){
                String setmealDishName = category.getName();
                setmealDto.setCategoryName(setmealDishName);
            }
            return setmealDto;
        }).collect(Collectors.toList());
        dtoPage.setRecords(list);
        return R.success(dtoPage);
    }

    /**
     * 新增套餐
     * @param setmealDto
     * @return
     */
    @PostMapping
 public R<String> save(@RequestBody SetmealDto setmealDto){
        log.info("套餐信息：{}",setmealDto);
        setmealService.saveWithDish(setmealDto);
        return R.success("新增套餐成功");
 }

    /**
     * 删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        log.info("ids:{}",ids);
        setmealService.removeWithDish(ids);
        return R.success("套餐数据删除成功");
    }

}
