package top.wennyy.service.serviceIml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.wennyy.entity.AddressBook;
import top.wennyy.mapper.AddressBookMapper;
import top.wennyy.service.AddressBookService;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
