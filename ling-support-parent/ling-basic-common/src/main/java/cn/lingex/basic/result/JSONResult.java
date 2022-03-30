package cn.lingex.basic.result;

import cn.lingex.basic.BusinessConstant;

import java.io.Serializable;

/**
 * @Description: 返回Result
 * @Author LIAOJIANBO
 * @Date 2020-12-13
 */
@SuppressWarnings("all")
public class JSONResult<T> implements Serializable {

    private String code = BusinessConstant.RESULT_SUCCESS_CODE;
    private Boolean status = BusinessConstant.RESULT_SUCCESS_STATUS;
    private T data;

    private JSONResult() {
    }


    private JSONResult(String code, Boolean msg, T data) {
        this.code = code;
        this.status = msg;
        this.data = data;
    }

    public static <T> JSONResult<T> getInstance(T t){
        JSONResult<T> result = new JSONResult<>();
        result.setData(t);
        return result;
    }

    public String getCode() {
        return code;
    }

    public JSONResult<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public JSONResult<T> setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public T getData() {
        return data;
    }

    public JSONResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "JSONResult{" +
                "code=" + code +
                ", msg='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public static void main(String[] args) {

    }
}
