package com.qa.parameters;

public class Manager {

    private String mobile;
    private String loginPwd;
    private String partnerCode;


    public Manager(){

    }

    public Manager(String mobile) {

        super();
       // this.loginPwd = loginPwd;
      //  this.partnerCode = partnerCode;

        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;    }
    public void setMobile(String account) {
        this.mobile = account;    }

  /*  public String getLoginPwd() {
        return loginPwd;    }
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;    }
    public String getPartnerCode() {
        return partnerCode;    }*/

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
        }
    }


