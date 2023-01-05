package top.wennyy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.wennyy.entity.Category;

/**
 * @Author HanWen
 * @Date 2022/11/26 14:29
 * @Version 1.0
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
