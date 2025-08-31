package com.firstapp.app1.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@SessionAttributes("name")
public class loginPageController {

    @Autowired // ✅ Inject AuthenticationService bean
    private AuthenticationService authenticationService;

    @RequestMapping(value = "login_jsp", method = RequestMethod.GET)
    public String GoToLoginPage() {
        return "login";
    }

    @RequestMapping(value = "login_jsp", method = RequestMethod.POST)
    public String gotoWelcomePage(@RequestParam String name,@RequestParam String password,ModelMap model) {

        if (authenticationService.authenticate(name, password)) {
            model.put("name", name);
            return "welcome"; // forward to welcome.jsp
        }

        model.put("errorMessage", "Invalid Credentials!!"); // add error message
        return "login"; // back to login.jsp
    }
}
