package icu.bystart.base.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一响应结果类
 * @param <T> 数据类型
 */
@Data
@ApiModel(description = "统一响应结果")
public class Result<T> {
    
    /**
     * 状态码
     */
    @ApiModelProperty("状态码")
    private Integer code;
    
    /**
     * 提示信息
     */
    @ApiModelProperty("提示信息")
    private String message;
    
    /**
     * 响应数据
     */
    @ApiModelProperty("响应数据")
    private T data;

    /**
     * 成功响应
     * @return Result对象
     * @param <T> 数据类型
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 成功响应
     * @param data 响应数据
     * @return Result对象
     * @param <T> 数据类型
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    /**
     * 错误响应
     * @param message 错误信息
     * @return Result对象
     * @param <T> 数据类型
     */
    public static <T> Result<T> error(String message) {
        return error(500, message);
    }

    /**
     * 错误响应
     * @param code 错误码
     * @param message 错误信息
     * @return Result对象
     * @param <T> 数据类型
     */
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
} 