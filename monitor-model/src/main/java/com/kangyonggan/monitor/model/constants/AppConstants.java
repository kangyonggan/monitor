package com.kangyonggan.monitor.model.constants;

/**
 * @author kangyonggan
 * @since 11/13/17
 */
public interface AppConstants {

    /**
     * Shiro Constants
     */
    String HASH_ALGORITHM = "SHA-1";
    int HASH_INTERATIONS = 2;
    int SALT_SIZE = 8;

    /**
     * 文件根路径的key
     */
    String FILE_PATH_ROOT = "file.root.path";

    /**
     * 文件上传路径
     */
    String FILE_UPLOAD = "upload/";

    /**
     * 把验证码存放在session中的key
     */
    String KEY_CAPTCHA = "key-captcha";

    /**
     * 分页大小
     */
    int PAGE_SIZE = 10;

    /**
     * 默认角色
     */
    String DEFAULT_ROLE_CODE = "ROLE_USER";

    /**
     * 是否删除
     */
    byte IS_DELETED_NO = 0;
    byte IS_DELETED_YES = 1;
}
