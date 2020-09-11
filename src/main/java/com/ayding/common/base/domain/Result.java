package com.ayding.common.base.domain;

import com.ayding.common.constant.ResultConstants;
import com.ayding.common.enums.ErrorEnum;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @author: dingxy
 * @date: 2020/09/09
 * @Description: 用于向前端返回统一的结果对象
 */
@Data
@Accessors(chain = true)
public class Result<T> {

    private int success = 0; // 操作标识，标记
    private String resultCode; // 结果编码
    private String message; // 提示信息
    private T model; // 结果对象
    private List<T> models; // 结果集对象
    private MyPageInfo pageInfo; // 分页信息对象
    private Object extra; // 扩展字段

    // 禁止空参构造
    private Result() {
    }

    // 通过操作标识及提示信息构建结果对象
    private static <T> Result<T> createWithSuccessFlag(int success) {
        Result result = new Result();
        result.setSuccess(success);
        return result;
    }

    public static <T> Result<T> createWithSuccessMessage() {
        Result result = createWithSuccessFlag(ResultConstants.YES);
        result.setResultCode(ResultConstants.OPERATION_SUCCESS);
        result.setMessage(ResultConstants.SUCCESS_MESSAGE);
        return result;
    }

    public static <T> Result<T> createWithSuccessMessage(String message) {
        Result result = createWithSuccessFlag(ResultConstants.YES);
        result.setResultCode(ResultConstants.OPERATION_SUCCESS);
        result.setMessage(StringUtils.isBlank(message) ? ResultConstants.SUCCESS_MESSAGE : message);
        return result;
    }

    public static <T> Result<T> createWithModel(T model) {
        Result result = createWithSuccessMessage();
        result.setModel(model);
        return result;
    }

    public static <T> Result<T> createWithModel(String message, T model) {
        Result result = createWithSuccessMessage(message);
        result.setModel(model);
        return result;
    }

    public static <T> Result<T> createWithModels(String message, List<T> models) {
        Result result = createWithSuccessMessage(message);
        result.setModels(models == null ? new ArrayList<>(0) : models);
        return result;
    }

    public static <T> Result<T> createWithModels(List<T> models) {
        Result result = createWithSuccessMessage();
        result.setModels(models == null ? new ArrayList<>(0) : models);
        return result;
    }

    public static <T> Result<T> createWithPaging(String message, List<T> models, MyPageInfo pagingInfo) {
        Result result = createWithModels(message, models == null ? new ArrayList<>(0) : models);
        result.setPageInfo(pagingInfo);
        return result;
    }

    public static <T> Result<T> createWithPaging(List<T> models, MyPageInfo pagingInfo) {
        Result result = createWithModels(models == null ? new ArrayList<>(0) : models);
        result.setPageInfo(pagingInfo);
        return result;
    }

    /**
     * 将PageHelper分页后的list转为分页信息
     */
    public static <T> Result<T> createWithPaging(String message, List<T> models) {
        Result result = createWithModels(message, models == null ? new ArrayList<>(0) : models);
        result.setPageInfo(restPage(models));
        return result;
    }

    /**
     * 将PageHelper分页后的list转为分页信息
     */
    public static <T> Result<T> createWithPaging(List<T> models) {
        Result result = createWithModels(models == null ? new ArrayList<>(0) : models);
        result.setPageInfo(restPage(models));
        return result;
    }

    public static <T> Result<T> createWithErrorMessage(String message, String errorCode) {
        Result result = createWithSuccessFlag(ResultConstants.NO);
        result.setMessage(message);
        result.setResultCode(errorCode);
        return result;
    }

    public static <T> Result<T> createWithErrorMessage(ErrorEnum errorEnum) {
        return createWithErrorMessage(errorEnum.getMsg(errorEnum.getCode()), errorEnum.getCode());
    }

    public static <T> Result<T> createWithError() {
        return createWithErrorMessage(ErrorEnum.ERROR);
    }


    /**
     * 将PageHelper分页后的list转为分页信息
     */
    public static <T> MyPageInfo restPage(List<T> list) {
        MyPageInfo myPageInfo = new MyPageInfo();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
//        myPageInfo.setTotalPage(pageInfo.getPages());
        myPageInfo.setPage(pageInfo.getPageNum());
        myPageInfo.setSize(pageInfo.getPageSize());
        myPageInfo.setTotal(pageInfo.getTotal());
//        myPageInfo.setList(pageInfo.getList());
        return myPageInfo;
    }
}
