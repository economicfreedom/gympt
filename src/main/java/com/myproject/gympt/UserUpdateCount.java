package com.myproject.gympt;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserUpdateCount {
    void userGptCountUpdate();

}
