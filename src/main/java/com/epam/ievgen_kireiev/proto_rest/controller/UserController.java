package com.epam.ievgen_kireiev.proto_rest.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.ievgen_kireiev.proto_rest.dao.UserDao;
import com.epam.ievgen_kireiev.proto_rest.entity.User;
import com.epam.ievgen_kireiev.proto_rest.protos.UserProtos;
import com.epam.ievgen_kireiev.proto_rest.service.UserService;
import com.google.protobuf.InvalidProtocolBufferException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/check/{usrlogin}/{usrpass}", method = RequestMethod.GET)
    public String checkLogin(@PathVariable String usrlogin,
            @PathVariable String usrpass, ModelMap model) {
        User user = userService.getUserByLogin(usrlogin);
        if (user.getPassword().equals(usrpass)) {
            model.addAttribute("login", usrlogin).addAttribute("result",
                    "login and password accepted");

        } else {
            model.addAttribute("login", "login not").addAttribute("result",
                    "accepted");
        }
        return "list";
    }

    @RequestMapping(value = "/register/{usrlogin}/{usrpass}", method = RequestMethod.GET)
    public String register(@PathVariable String usrlogin,
            @PathVariable String usrpass, ModelMap model) {
        User user = new User();
        user.setLogin(usrlogin);
        user.setPassword(usrpass);
        userService.addUser(user);
        model.addAttribute("login", usrlogin);
        model.addAttribute("result", " sucesfully registered");
        return "list";
    }

    @RequestMapping(value = "/get/{usrlogin}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void getUserByLogin(@PathVariable String usrlogin, HttpServletResponse response) {

        User user = userService.getUserByLogin(usrlogin);
        if (user == null) {
            user = new User();
            user.setLogin("not found");
        }
        byte[] userdata = UserProtos.User.newBuilder().setLogin(user.getLogin()).build().toByteArray();

        try {
            OutputStream os = response.getOutputStream();
            os.write(userdata);
            os.flush();
            os.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    @RequestMapping(value = "/parse/{usrlogin}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public String getUserByLogin(@PathVariable String usrlogin, ModelMap model, HttpServletRequest request) {
        byte[] userdata = null;
        //URL url = new URL("http://localhost:8080/ProtoRestServer/").openStream();
        try {
            InputStream is = new URL("http://localhost:8080/ProtoRestServer/get/111").openStream();;
            userdata = new byte[is.available()];
            is.read(userdata);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (userdata != null) {
            model.addAttribute("login", usrlogin);
            model.addAttribute("result", " sucesfully parsed");
            return "list";
        }
        model.addAttribute("login", usrlogin);
        model.addAttribute("result", " NULL DATA");
        return "list";

    }

/////////////test method DELETEME        
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index(ModelMap model) {

        byte[] userdata = null;

        try {
            InputStream is = new URL("http://localhost:8080/ProtoRestServer/user/get/TestLogin").openStream();;
            userdata = new byte[is.available()];
            is.read(userdata);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (userdata != null) {
            try {
                UserProtos.User userProtos = UserProtos.User.parseFrom(userdata);
                model.addAttribute("login", userProtos.getLogin());
            } catch (InvalidProtocolBufferException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                model.addAttribute("login", "protobuf");
                model.addAttribute("result", " CRASH");
            }

            return "list";
        }
        model.addAttribute("login", "SOMETHING");
        model.addAttribute("result", " WRONG");
        return "list";
    }
}
