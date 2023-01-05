package top.wennyy.dto;


import lombok.Data;
import top.wennyy.entity.Setmeal;
import top.wennyy.entity.SetmealDish;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
