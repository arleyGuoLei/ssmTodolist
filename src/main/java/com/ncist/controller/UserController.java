package com.ncist.controller;

import com.ncist.model.User;
import com.ncist.service.IUserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private HttpServletRequest request;

//    @RequestMapping("/select")
//    public ModelAndView selectUser() throws Exception {
//        ModelAndView mv = new ModelAndView();
//        User user = userService.selectUser(1);
//        mv.addObject("user", user);
//        mv.setViewName("user");
//        return mv;
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestParam("username") String username, @RequestParam("password") String password) {
        JSONObject jsonObject = new JSONObject();
        if ("".equals(username) && "".equals(password)){
            jsonObject.put("data", "用户名和密码不能为空");
            jsonObject.put("status", false);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(this.getMd5(password));
        User userInfo = userService.login(user);
        if(userInfo == null){
            jsonObject.put("status", false);
            jsonObject.put("data", "账号或密码错误!");
            return jsonObject;
        } else {
            request.getSession().setAttribute("user", username);
            jsonObject.put("status", true);
            jsonObject.put("data", "登录成功~");
            return jsonObject;
        }
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String  logout() {
        request.getSession().removeAttribute("user");
        return "redirect:/login.html";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject register(@RequestParam("username") String username, @RequestParam("password") String password) {
        JSONObject jsonObject = new JSONObject();
        if ("".equals(username) && "".equals(password)){
            jsonObject.put("data", "用户名和密码不能为空");
            jsonObject.put("status", false);
            return jsonObject;
        }
        System.out.println("username: " + username);

        User user = userService.selectByUsername(username);
        if(user == null){
            User userInfo = new User();
            userInfo.setUsername(username);
            userInfo.setPassword(this.getMd5(password));
            int num = userService.insertUser(userInfo);
            System.out.println(num);
            if(num != 0){
                jsonObject.put("status", true);
                jsonObject.put("data", "注册成功!");
                return jsonObject;
            } else {
                jsonObject.put("status", false);
                jsonObject.put("data", "注册失败,请重试!");
                return jsonObject;
            }
        } else {
            // 账号存在
            System.out.println(user);
            jsonObject.put("status", false);
            jsonObject.put("data", "用户已存在!");
            return jsonObject;
        }
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getInfo() {
        JSONObject jsonObject = new JSONObject();
        Object username =  request.getSession().getAttribute("user");
        if(username != null){
            jsonObject.put("status", true);
            jsonObject.put("data", (String) username);
        } else {
            jsonObject.put("status", false);
            jsonObject.put("data", "未登录");
        }
        return jsonObject;
    }


    /**
     * Encodes a string 2 MD5
     *
     * @param str String to encode
     * @return Encoded String
     * @throws NoSuchAlgorithmException
     */
    public static String getMd5(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }

}
