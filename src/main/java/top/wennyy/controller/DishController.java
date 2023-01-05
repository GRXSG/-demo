package top.wennyy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.wennyy.common.R;
import top.wennyy.dto.DishDto;
import top.wennyy.entity.Category;
import top.wennyy.entity.Dish;
import top.wennyy.service.CategoryService;
import top.wennyy.service.DishFlavorService;
import top.wennyy.service.DishService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author HanWen
 * @Date 2022/12/5 13:30
 * @Version 1.0
 */
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 菜品分页构造
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize,String name){
        //分页构造器
        Page<Dish> pageinfo = new Page<>(page, pageSize);
        //创建一个空的分页构造器
        Page<DishDto> dishDtoPage = new Page<>();
        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(name != null,Dish::getName,name);
        //添加排序条件，根据sort进行排序
        queryWrapper.orderByAsc(Dish::getSort);

        //把查询出来的数据进行分页查询
        dishService.page(pageinfo,queryWrapper);
        //对象拷贝,把pageinfo的属性拷贝到dishDtoPage,忽略复制records属性
        BeanUtils.copyProperties(pageinfo,dishDtoPage,"records");
        //把dish的数据赋值给records
        List<Dish> records = pageinfo.getRecords();

    List<DishDto> list =
        records.stream()
            .map(
                (item) -> {
                  DishDto dishDto = new DishDto();
                  // 把item（即records)中的数据完全赋值给dishDto对象
                  BeanUtils.copyProperties(item, dishDto);
                  Long categoryId = item.getCategoryId(); // 分类id
                  // 根据分类id查询数据
                  Category category = categoryService.getById(categoryId);
                  // 把数据中的name属性赋值
                  if (category != null) {
                    String categoryName = category.getName();
                      // 把查询出来的name属性填进dishDto的CategoryName当中去
                      dishDto.setCategoryName(categoryName);
                  }

                  return dishDto;
                })
            .collect(Collectors.toList());
        dishDtoPage.setRecords(list);
        return R.success(dishDtoPage);
    }

    /**
     * 新增菜品
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        log.info(dishDto.toString());
        dishService.saveWithFlavor(dishDto);
        return R.success("新增菜品成功");
    }

    /**
     * 根据id查询菜品信息和对应的口味信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id){
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }

    /**
     * 修改菜品
     * @param dishDto
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){
        log.info(dishDto.toString());
        dishService.updateWithFlavor(dishDto);
        return R.success("修改菜品成功");
    }

    /**
     * 根据条件查询对应的菜品数据
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public R<List<Dish>> list(Dish dish){
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId()!=null,Dish::getCategoryId,dish.getCategoryId());
        //添加条件，查询状态为1（起售状态）的菜品
        queryWrapper.eq(Dish::getStatus,1);
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        List<Dish> list = dishService.list(queryWrapper);
        return R.success(list);
    }

}
