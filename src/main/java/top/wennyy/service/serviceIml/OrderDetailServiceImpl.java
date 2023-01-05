package top.wennyy.service.serviceIml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import top.wennyy.entity.OrderDetail;
import top.wennyy.mapper.OrderDetailMapper;
import top.wennyy.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}