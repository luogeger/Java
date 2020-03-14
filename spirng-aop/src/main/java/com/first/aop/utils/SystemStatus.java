package com.first.aop.utils;


/**
 * @author chengang
 * time 2018-5-5
 */
public enum SystemStatus {

    /** 参数错误 **/
    SUCCESS(1, "200", "操作成功",""),
    /** 参数错误 **/
    LOGIN_SUCCESS(1, "200", "loginSuccess",""),
    /** 参数错误 **/
    ERROR(0, "500", "系统繁忙，请稍后再试","/toLogin"),
    /** 参数错误 **/
    MISS_SYSTEM_INFO(0, "1001", "缺失系统信息",""),
    /** 参数错误 **/
    PARAM_ERROR_CODE(0, "400", "参数错误", ""),
    /** 登录超时 **/
    LOGIN_TIMEOUT(0, "401", "login_timeout", ""),
    /** token 过期 **/
    TOKEN_TIMEOUT_CODE(0, "402", "token 过期", ""),
    /** 禁止访问 **/
    NO_AUTH_CODE(0, "403", "禁止访问", ""),
    /** 资源没找到 **/
    NOT_FOUND(0, "404", "资源没找到", ""),
    /** 服务器错误 **/
    SERVER_ERROR_CODE(0, "500", "服务器错误", ""),
    /** 限制调用 **/
    LIMIT_ERROR_CODE(0, "501", "限制调用", ""),
    /** 服务降级中 **/
    DOWNGRADE(0, "406", "服务降级中", ""),

    //企业用户相关
    /** 企业用户分页查询_参数不能为空 */
    ENTERPRISE_QUERY_PARAMS(0, "400", "参数不能为空", ""),
    ENTERPRISE_AUDIT_ERROR(0, "502", "执行审核操作，服务器异常", ""),
    //运营人员相关
    OPERATOR_ENABLE_ERROR(0, "500", "运营人员启禁用操作异常，请刷新页面或稍后重试", ""),
    OPERATOR_DELETE_ERROR(0, "500", "运营人员删除操作异常，请刷新页面或稍后重试", ""),
    /** 展会观众导出_保存导出记录异常 */
    AUDIENCE_EXPORT_SAVE(0, "500", "保存导出记录异常", ""),
    //企业员工管理相关
    /** 启用禁用企业用户_用户ID不能为空 */
    STAFF_USER_ENABLE_STAFF_OBJECT(0, "400", "参数值不能为空", ""),
    /** 启用禁用企业用户_用户ID不能为空 */
    STAFF_USER_ENABLE_USER_ID(0, "400", "用户ID不能为空", ""),
    /** 启用禁用企业用户_用户启禁用状态不能为空 */
    STAFF_USER_ENABLE_STATUS(0, "400", "启用禁用状态不能为空", ""),
    /** 启用禁用企业用户_展会ID不能为空 */
    STAFF_USER_ENABLE_EXHIBITION_ID(0, "400", "展会ID不能为空", ""),
    /** 删除企业用户_用户ID集合不能为空 */
    STAFF_USER_DELETE_IDLIST(0, "400", "用户ID集合不能为空", ""),
    /** 新增编辑企业用户_用户姓名不能为空 */
    STAFF_ADD_USER_NAME(0, "400", "用户姓名不能为空", ""),
    /** 新增编辑企业用户_用户姓名不能超过20个字符 */
    STAFF_ADD_USER_NAME_LARGE(0, "400", "用户姓名不能超过20个字符", ""),
    /** 新增编辑企业用户_用户手机号不能为空 */
    STAFF_ADD_USER_MOBILE(0, "400", "用户手机号不能为空", ""),
    /** 新增编辑企业用户_用户手机号只能为数字 */
    STAFF_ADD_USER_MOBILE_NUMBERIC(0, "400", "用户手机号只能为数字", ""),
    /** 新增编辑企业用户_用户手机号不是11位且1开头 */
    STAFF_ADD_USER_MOBILE_RULES(0, "400", "用户手机号不是11位且1开头", ""),
    /** 新增编辑企业用户_用户角色ID不能为空 */
    STAFF_ADD_USER_ROLE(0, "400", "用户角色ID不能为空", ""),
    /** 新增编辑企业用户_已成为其他企业用户，不能创建用户 */
    STAFF_ADD_USER_CHECK_PUID(0, "500", "当前手机号码已经归属其他公司，请联系客服人员", ""),
    /** 新增编辑企业用户_已成为其他企业用户，不能创建用户 */
    STAFF_ADD_USER_FAILURE(0, "500", "新增或编辑失败", ""),
    /** 新增编辑企业用户_展会用户关系已存在，使用编辑操作 */
    STAFF_ADD_USER_FAILURE_EDIT(0, "500", "当前手机号码已存在，请重试", ""),
    /** 新增编辑企业用户_编辑后的手机号已存在，无法更新 */
    STAFF_ADD_USER_FAILURE_MOBILE(0, "500", "编辑后的手机号已存在，无法更新", ""),
    /** 新增编辑企业用户_无法添加企业认证通过用户 */
    STAFF_ADD_USER_FAILURE_VERIFY(0, "500", "无法添加企业认证通过用户", ""),
    /** 运营平台-删除角色 */
    OPERATOR_ROLE_DELETE_ID_NULL(0, "400", "角色id不能为空", ""),
    /** 参数错误 **/
    OPERATOR_ROLE_DELETE_ADMIN_ERROR(0, "500", "当前用户非超级管理员", ""),
    /** 参数错误 **/
    OPERATOR_ROLE_DELETE_ERROR(0, "500", "角色删除失败", ""),
    /**展会负责人只允许一个*/
    OPERATOR_ROLE_TYPE_ERROR(0, "500", "展会负责人只允许设置一个", ""),

    //基础数据字典查询
    SYSTEM_DICT_PARAMS(0, "400", "参数不能为空", ""),
    /** 参数错误 **/
    SYSTEM_DICT_PARAMS_FUNCTION_TYPE(0, "400", "字典类型参数不能为空", ""),
    /** 参数错误 **/
    SYSTEM_DICT_PARAMS_DICT_ID(0, "400", "字典ID参数不能为空", ""),

    //图片上传相关
    PIC_ROOM_ERROR_FILES_NULL(0, "400", "上传文件不存在", ""),
    /** 参数错误 **/
    PIC_ROOM_ERROR_PIC_ID_NULL(0, "400", "处理图片ID为空", ""),
    /** 参数错误 **/
    PIC_ROOM_ERROR_RESIZE_CONTENT_NULL(0, "400", "处理类型为空", ""),

    /** 用户名或密码错误 **/
    NAMEORPWD_ERROR(0, "20001", "nameOrPwdError", ""),
    /** 验证码key缺失 **/
    INVALID_CAPTCHAKEY(0, "20002", "InvalidCaptchaKey", ""),
    /** 验证码必填 **/
    VERIFICATIONCODE_MISSING(0, "20003", "VerificationCodeMissing", ""),
    /** 验证码不正确 **/
    VALIDCODE_ERROR(0, "20004", "validCodeError", ""),
    /** 账号已被禁用 **/
    DISABLED_LOGIN(0, "20005", "loginDisabled", ""),
    /** 不合法的登录平台 **/
    INVALID_LOGINPLATFORMTYPE(0, "20006", "InvalidLoginPlatformType", ""),
    /** 验证码已过期 **/
    VALIDCODE_EXPIRED(0, "20007", "validCodeExpired", ""),
    /** 查询无此用户 **/
    USER_NOT_EXIT(0, "20008", "userNotExit", ""),
    /** 密码跟确认密码不一致 **/
    PASSWORD_IS_INCONSISTENT(0, "20009", "passwordIsInconsistent", ""),
    /** 新密码不能与原始密码相同 **/
    NEW_PASSWORD_CANNOT_SAME(0, "200010", "newPasswordCannotSame", ""),
    /** 新密码长度格式不正确，请更改 **/
    NEW_PASSWORD_NOT_FORMATTED(0, "20011", "newPasswordNotFormatted", ""),
    /** 密码格式不正确，请更改 **/
    PASSWORD_FORMAT_INCORRECT(0, "20012", "PasswordFormatIncorrect", ""),
    /** 原密码输入错误 **/
    ORIGINAL_PASSWORD_INCORRECTLY(0, "20013", "originalPasswordIncorrectly", "");


    private int status;
    private String code;
    private String message;
    private String linkUrl;

    SystemStatus(int status, String code, String message, String linkUrl) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.linkUrl = linkUrl;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
