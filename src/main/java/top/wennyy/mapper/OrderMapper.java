package top.wennyy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import top.wennyy.entity.Orders;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}