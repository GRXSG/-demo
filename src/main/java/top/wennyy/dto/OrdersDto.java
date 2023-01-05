package top.wennyy.dto;


import lombok.Data;
import top.wennyy.entity.OrderDetail;
import top.wennyy.entity.Orders;

import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
