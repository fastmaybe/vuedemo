package com.example.vuedemo.mapper;


import com.example.vuedemo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insert(User user);

}
