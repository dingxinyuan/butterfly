package com.ayding.butterfly.posts.service.impl;

import com.ayding.butterfly.posts.dao.auto.PlumemoPostsMapper;
import com.ayding.butterfly.posts.domain.po.PlumemoPosts;
import com.ayding.butterfly.posts.domain.po.PlumemoPostsExample;
import com.ayding.butterfly.posts.domain.vo.PostsVO;
import com.ayding.butterfly.posts.service.PostsService;
import com.ayding.common.base.domain.Result;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Created with IntelliJ IDEA.
 * @author: dingxy
 * @date: 2020/09/09
 * @Description:
 */
@Service
@Slf4j
public class PostsServiceImpl implements PostsService{

    @Autowired
    private PlumemoPostsMapper plumemoPostsMapper;


    @Override
    public Result<PlumemoPosts> getPostsList(PostsVO postsVO) {
//        postsVO = Optional.ofNullable(postsVO).orElse(new PostsVO());
        PageHelper.startPage(postsVO.getPage(), postsVO.getSize());
        PlumemoPostsExample plumemoPostsExample = new PlumemoPostsExample();
        List<PlumemoPosts> plumemoPosts = plumemoPostsMapper.selectByExample(plumemoPostsExample);
        return Result.createWithPaging(plumemoPosts);
    }
}
