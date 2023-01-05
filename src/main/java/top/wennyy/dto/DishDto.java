package top.wennyy.dto;


import lombok.Data;
import top.wennyy.entity.Dish;
import top.wennyy.entity.DishFlavor;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
