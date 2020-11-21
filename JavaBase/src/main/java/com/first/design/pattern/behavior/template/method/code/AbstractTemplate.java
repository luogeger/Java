package com.first.design.pattern.behavior.template.method.code;

/**
 * @author luoxiaoqing
 */
public abstract class AbstractTemplate {

    /**
     * 发送短信、图片验证码的模板方法
     */
    public final void sendCodeTemplate() {
        generate();
        send();
        validate();

        if (visitCustomersHook()) {
            visitCustomers();
        }
    }

    protected void visitCustomers() {
        System.out.println("回访用户了");
    };

    /**
     * 是否要回访用户
     * @return
     */
    protected boolean visitCustomersHook() {
        return true;
    };

    abstract void validate();

    protected void send() {
        System.out.println("发送给用户");
    };


    abstract void generate();


}
