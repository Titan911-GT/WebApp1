package com.firstapp.app1.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.security.core.Authentication; // ✅ Correct one
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@SessionAttributes("name")
public class WELCOMEController {

    // @Autowired // ✅ Inject AuthenticationService bean
    // private AuthenticationService authenticationService;


    // AUTHENTICATION PAGE IS NOT NEEDED

    // LOGIN JSP IS ALSO TO BE DELETED

    //  CHANGE OF GoToLoginPage --->   GoToWELCOMEPage

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String GoToWELCOMEPage(ModelMap model) {
        model.put("name", GetLoggedInUsername());
        return "welcome";
    }

    public String GetLoggedInUsername(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    } 




}
