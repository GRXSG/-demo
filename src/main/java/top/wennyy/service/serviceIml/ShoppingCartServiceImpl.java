package top.wennyy.service.serviceIml;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.wennyy.entity.ShoppingCart;
import top.wennyy.mapper.ShoppingCartMapper;
import top.wennyy.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
