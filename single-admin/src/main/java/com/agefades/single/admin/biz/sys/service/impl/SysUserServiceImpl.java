package com.agefades.single.admin.biz.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.agefades.single.admin.biz.sys.dto.ClientMenuDTO;
import com.agefades.single.admin.biz.sys.resp.SysUserQueryResp;
import com.agefades.single.admin.biz.sys.entity.SysUser;
import com.agefades.single.admin.biz.sys.mapper.SysUserMapper;
import com.agefades.single.admin.biz.sys.req.SysUserAddReq;
import com.agefades.single.admin.biz.sys.req.SysUserQueryReq;
import com.agefades.single.admin.biz.sys.service.SysUserService;
import com.agefades.single.admin.enums.AdminResultCodeEnum;
import com.agefades.single.common.base.PageReq;
import com.agefades.single.common.util.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public Page<SysUserQueryResp> conditionPage(SysUserQueryReq req) {
        Page<SysUser> page;
        if (req == null) {
            page = page(new PageReq<SysUser>().page());
        } else {
            page = lambdaQuery()
                    .like(StrUtil.isNotBlank(req.getPhone()), SysUser::getPhone, req.getPhone())
                    .like(StrUtil.isNotBlank(req.getUsername()), SysUser::getUsername, req.getUsername())
                    .eq(ObjectUtil.isNotNull(req.getIsEnable()), SysUser::getIsEnable, req.getIsEnable())
                    .page(req.page());
        }

        Page<SysUserQueryResp> result = new Page<>();
        BeanUtil.copyProperties(page, result);

        if (CollUtil.isNotEmpty(page.getRecords())) {
            result.setRecords(page.getRecords().stream()
                    .map(v -> BeanUtil.copyProperties(v, SysUserQueryResp.class))
                    .collect(Collectors.toList())
            );
        }

        return result;
    }

    @Override
    public void add(SysUserAddReq req) {
        // 1. 唯一索引校验
        Assert.isTrue(lambdaQuery().eq(SysUser::getUsername, req.getUsername()).count() == 0, AdminResultCodeEnum.USER_REPEAT_USERNAME);
        Assert.isTrue(lambdaQuery().eq(SysUser::getPhone, req.getPhone()).count() == 0, AdminResultCodeEnum.USER_REPEAT_PHONE);

        // 2. 密码加密
        req.setPassword(DigestUtil.bcrypt(req.getPassword()));

        // 3. 保存用户
        save(BeanUtil.copyProperties(req, SysUser.class));
    }

    @Override
    public List<ClientMenuDTO> getPermission(String userId) {
        return baseMapper.getPermission(userId);
    }

}