package com.kangyonggan.monitor.web.controller.dashboard;

import com.kangyonggan.monitor.biz.service.UserService;
import com.kangyonggan.monitor.model.vo.ShiroUser;
import com.kangyonggan.monitor.model.vo.User;
import com.kangyonggan.monitor.web.controller.BaseController;
import com.kangyonggan.monitor.web.util.FileUpload;
import com.kangyonggan.monitor.web.util.Images;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 5/8/17
 */
@Controller
@RequestMapping("dashboard/user/info")
public class DashboardUserInfoController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 基本信息
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("USER_INFO")
    public String info(Model model) {
        ShiroUser shiroUser = userService.getShiroUser();
        User user = userService.findUserByUsername(shiroUser.getUsername());

        model.addAttribute("user", user);
        return getPathIndex();
    }

    /**
     * 基本信息
     *
     * @param user
     * @param result
     * @param avatar
     * @return
     * @throws FileUploadException
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("USER_INFO")
    public Map<String, Object> info(@ModelAttribute(value = "user") @Valid User user, BindingResult result,
                                    @RequestParam(value = "avatar", required = false) MultipartFile avatar) throws FileUploadException {
        Map<String, Object> resultMap = getResultMap();
        ShiroUser shiroUser = userService.getShiroUser();

        if (!result.hasErrors()) {
            user.setUsername(shiroUser.getUsername());

            if (avatar != null && !avatar.isEmpty()) {
                String fileName = FileUpload.upload(avatar);

                String large = Images.large(fileName);
                user.setLargeAvatar(large);
                String middle = Images.middle(fileName);
                user.setMediumAvatar(middle);
                String small = Images.small(fileName);
                user.setSmallAvatar(small);
            }

            userService.updateUserByUsername(user);
            if (StringUtils.isNotEmpty(user.getPassword())) {
                userService.updateUserPassword(user);
            }

            user = userService.findUserByUsername(shiroUser.getUsername());

            resultMap.put("user", user);
        } else {
            setResultMapFailure(resultMap);
        }

        return resultMap;
    }

}
