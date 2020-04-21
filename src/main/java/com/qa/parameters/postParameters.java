

package com.qa.parameters;

public class postParameters {


    private String mobile;
    private String pwd;

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public postParameters(String pwd) {
        this.pwd = pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public postParameters(){

    }
    //login
    public postParameters(String mobile , String pwd){
        this.mobile = mobile;
        this.pwd = pwd;
    }

}