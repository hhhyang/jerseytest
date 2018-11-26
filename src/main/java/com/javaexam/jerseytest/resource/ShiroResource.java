package com.javaexam.jerseytest.resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/shiro")
public class ShiroResource {

    private static final Logger LOG = LoggerFactory.getLogger(ShiroResource.class);

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public String login(HttpServletRequest request){

        LOG.info("enter login");

        Subject subject = SecurityUtils.getSubject();
        subject.isAuthenticated();
        UsernamePasswordToken token = new UsernamePasswordToken("user1", "pwd1");
        try {
            subject.login(token);
            return "ok";
        }catch (LockedAccountException lae) {
            token.clear();
            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
            return "login";
        } catch (AuthenticationException e) {
            token.clear();
            request.setAttribute("msg", "用户或密码不正确！");
            return "login";
        }
    }

    @GET
    @Path("/usersPage")
    @Produces(MediaType.APPLICATION_JSON)
    public String usersPage(){
        return "user/users";
    }

    @GET
    @Path("/rolesPage")
    @Produces(MediaType.APPLICATION_JSON)
    public String rolesPage(){
        return "role/roles";
    }

    @GET
    @Path("/resourcesPage")
    @Produces(MediaType.APPLICATION_JSON)
    public String resourcesPage(){
        return "resources/resources";
    }

    @GET
    @Path("/403")
    @Produces(MediaType.APPLICATION_JSON)
    public String forbidden(){
        return "403";
    }

}
