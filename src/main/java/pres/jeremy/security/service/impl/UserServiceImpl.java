package pres.jeremy.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pres.jeremy.security.entity.User;
import pres.jeremy.security.mapper.UserMapper;
import pres.jeremy.security.service.IUserService;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User findByUserName(String userName) {
        return baseMapper.findByUserName(userName);
    }
}