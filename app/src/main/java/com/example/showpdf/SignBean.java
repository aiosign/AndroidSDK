package com.example.showpdf;

/**
 * 作者：zjt on 2020/9/17.
 * 邮箱：13120490031@163.com
 * 版本：v1.0
 * qq：1158710428
 */
public class SignBean {


    /**
     * return_code : 1000
     * return_message : success
     * result_code : 0
     * result_message : 操作成功
     * data : {"sign":"D7CF920856398970E47815D83BD4CE89","sign_type":"md5"}
     */

    private String return_code;
    private String return_message;
    private String result_code;
    private String result_message;
    private DataBean data;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_message() {
        return return_message;
    }

    public void setReturn_message(String return_message) {
        this.return_message = return_message;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getResult_message() {
        return result_message;
    }

    public void setResult_message(String result_message) {
        this.result_message = result_message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sign : D7CF920856398970E47815D83BD4CE89
         * sign_type : md5
         */

        private String sign;
        private String sign_type;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getSign_type() {
            return sign_type;
        }

        public void setSign_type(String sign_type) {
            this.sign_type = sign_type;
        }
    }
}
