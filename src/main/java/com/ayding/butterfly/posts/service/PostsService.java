package com.ayding.butterfly.posts.service;

import com.ayding.butterfly.posts.domain.po.PlumemoPosts;
import com.ayding.butterfly.posts.domain.vo.PostsVO;
import com.ayding.common.base.domain.Result;

/**
 * @Created with IntelliJ IDEA.
 * @author: dingxy
 * @date: 2020/09/09
 * @Description:
 */
public interface PostsService {


    /**
     * 查询文章的列表
     * @param postsVO
     * @return
     */
    Result<PlumemoPosts> getPostsList(PostsVO postsVO);

}
