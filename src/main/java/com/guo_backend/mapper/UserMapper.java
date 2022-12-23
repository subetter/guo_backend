package com.guo_backend.mapper;

import com.guo_backend.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
* @author fu
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-12-04 23:25:27
* @Entity generator.domain.User
*/
@Component
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据id查询用户信息为 Map集合
     * @param
     * @return
     */


}




