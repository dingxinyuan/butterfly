package com.ayding.butterfly.posts.controller;

import com.ayding.butterfly.posts.domain.po.PlumemoPosts;
import com.ayding.butterfly.posts.domain.vo.PostsVO;
import com.ayding.butterfly.posts.service.PostsService;
import com.ayding.common.annotation.OperateLog;
import com.ayding.common.base.domain.Result;
import com.ayding.common.enums.OperateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created with IntelliJ IDEA.
 * @author: dingxy
 * @date: 2020/09/09
 * @Description:
 */
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostsService postsService;


    @OperateLog(module = "文章列表", code= OperateEnum.GET_POSTS_LIST)
    @GetMapping("/posts/v1/list")
    public Result<PlumemoPosts> getPostsList(PostsVO postsVO) {
        return postsService.getPostsList(postsVO);
    }


}
