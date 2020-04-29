package pres.jeremy.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pres.jeremy.security.entity.User;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where user_name=#{userName}")
    User findByUserName(@Param("userName") String userName);
}
