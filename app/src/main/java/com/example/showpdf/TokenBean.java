package com.example.showpdf;

/**
 * 作者：zjt on 2020/9/16.
 * 邮箱：13120490031@163.com
 * 版本：v1.0
 * qq：1158710428
 */
public class TokenBean {


    /**
     * return_code : 1000
     * return_message : success
     * result_code : 0
     * result_message : 处理成功
     * data : {"access_token":"3fda9b33-9aef-4e32-974c-24ad195871fe","scope":"token","token_type":"bearer","expires_in":5615}
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
         * access_token : 3fda9b33-9aef-4e32-974c-24ad195871fe
         * scope : token
         * token_type : bearer
         * expires_in : 5615
         */

        private String access_token;
        private String scope;
        private String token_type;
        private int expires_in;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getToken_type() {
            return token_type;
        }

        public void setToken_type(String token_type) {
            this.token_type = token_type;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }
    }
}
