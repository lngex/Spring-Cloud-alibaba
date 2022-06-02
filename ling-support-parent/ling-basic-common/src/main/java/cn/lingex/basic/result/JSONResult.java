package cn.lingex.basic.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: 返回Result
 * @Author SUHAO
 * @Date 2020-12-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@SuppressWarnings("all")
public class JSONResult<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    public JSONResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> JSONResult<T> success() {
        return new JSONResult<T>(ResultCodeEnum.OK.getCode(), ResultCodeEnum.OK.getMessage());
    }

    public static <T> JSONResult<T> success(T data) {
        return new JSONResult<T>(ResultCodeEnum.OK.getCode(), ResultCodeEnum.OK.getMessage(), data);
    }

    public static <T> JSONResult<T> success(String msg, T data) {
        return new JSONResult<T>(ResultCodeEnum.OK.getCode(), msg, data);
    }

    public static <T> JSONResult<T> failure(int code, String msg) {
        return new JSONResult<T>(code, msg);
    }

    public static <T> JSONResult<T> failure() {
        return new JSONResult<T>(ResultCodeEnum.FAILURE.getCode(), ResultCodeEnum.FAILURE.getMessage());
    }

    public static <T> JSONResult<T> failure(String msg) {
        return new JSONResult<T>(ResultCodeEnum.FAILURE.getCode(), msg);
    }

    public static <T> JSONResult<T> failure(ResultCodeEnum codeEnum) {
        return new JSONResult<T>(codeEnum.getCode(), codeEnum.getMessage());
    }

    public static <T> JSONResult<T> error(ResultCodeEnum codeEnum) {
        return new JSONResult<T>(codeEnum.getCode(), codeEnum.getMessage());
    }

    public static <T> JSONResult<T> tokenExpire(String msg) {
        return new JSONResult<T>(ResultCodeEnum.TOKEN_INVALID.getCode(), msg);
    }
//    @Override
//    public String toString() {
//        return JSONObject.toJSONString(this, SerializerFeature.WriteEnumUsingToString);
//    }
//

}
