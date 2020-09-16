package com.ayding.butterfly.auth.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.ayding.butterfly.auth.dao.AuthTokenDao;
import com.ayding.butterfly.auth.dao.AuthUserDao;
import com.ayding.butterfly.auth.domain.po.AuthToken;
import com.ayding.butterfly.auth.domain.po.AuthUser;
import com.ayding.butterfly.auth.domain.vo.AuthUserVO;
import com.ayding.butterfly.auth.service.OauthService;
import com.ayding.common.base.domain.Result;
import com.ayding.common.constant.Constants;
import com.ayding.common.enums.ErrorEnum;
import com.ayding.common.util.ExceptionUtil;
import com.ayding.common.util.JwtUtil;
import com.ayding.system.enums.RoleEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Date;

/**
 * @author: ayding
 * @date: 2019/09/04 08:58
 */
@Service
@Slf4j
public class OauthServiceImpl implements OauthService {

    @Autowired
    private AuthUserDao authUserDao;

    @Autowired
    private AuthTokenDao authTokenDao;

    @Override
    public Result login(AuthUserVO authUserVO) {
        log.debug("login {}", authUserVO);
        if (authUserVO == null || StringUtils.isBlank(authUserVO.getEmail()) || StringUtils.isBlank(authUserVO.getPassword())) {
            ExceptionUtil.rollback(ErrorEnum.PARAM_ERROR);
        }

        AuthUser authUser = authUserDao.selectOne(new LambdaQueryWrapper<AuthUser>()
                .eq(AuthUser::getRoleId, RoleEnum.ADMIN.getRoleId())
                .eq(AuthUser::getEmail, authUserVO.getEmail()));
        ExceptionUtil.isRollback(authUser == null, ErrorEnum.ACCOUNT_NOT_EXIST);

        String psw = SecureUtil.md5(authUserVO.getPassword());
        ExceptionUtil.isRollback(!authUser.getPassword().equals(psw), ErrorEnum.PASSWORD_ERROR);

        authUserVO.setRoles(Collections.singletonList(RoleEnum.getEnumTypeMap().get(authUser.getRoleId()).getRoleName()));
        authUserVO.setCreateTime(authUser.getCreateTime());
        String token = JwtUtil.getToken(new AuthUserVO().setPassword(authUser.getPassword()).setName(authUser.getName()).setId(authUser.getId()));
        authUserVO.setToken(token);
        authTokenDao.insert(new AuthToken().setUserId(authUser.getId()).setToken(token).setExpireTime(new Date(Constants.EXPIRE_TIME + System.currentTimeMillis()).toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime()));

        return Result.createWithModel(authUserVO);
    }
    
}
