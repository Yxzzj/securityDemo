package pres.jeremy.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pres.jeremy.security.entity.User;

public interface IUserService extends IService<User> {

    User findByUserName(String userName);
}
