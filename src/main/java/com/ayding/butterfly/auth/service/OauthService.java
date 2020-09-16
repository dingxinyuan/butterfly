package com.ayding.butterfly.auth.service;

import com.ayding.common.base.domain.Result;
import com.ayding.butterfly.auth.domain.vo.AuthUserVO;

/**
 * @description:
 * @author: ayding
 * @date: 2019/09/04 08:58
 */
public interface OauthService {


    /**
     * 保存管理员
     */
    Result login(AuthUserVO authUserVO);

}
