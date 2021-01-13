package com.agefades.single.admin.biz.sys.mapper;

import com.agefades.single.admin.biz.sys.dto.ClientMenuDTO;
import com.agefades.single.admin.biz.sys.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<ClientMenuDTO> getPermission(@Param("userId") String userId);

}
