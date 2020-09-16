package com.ayding.butterfly.auth.dao;

import com.ayding.common.base.dao.BaseDao;
import com.ayding.butterfly.auth.domain.po.AuthUser;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author byteblogs
 * @since 2019-08-28
 */
public interface AuthUserDao extends BaseDao<AuthUser> {

    String selectAvatar();
    AuthUser selectAdmin();

}
