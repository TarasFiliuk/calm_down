package com.epam.ua.trainingProject.controller;

import com.epam.ua.trainingProject.error.Errors;
import com.epam.ua.trainingProject.facade.UserFacade;
import com.epam.ua.trainingProject.models.User;
import com.epam.ua.trainingProject.service.StorageService;
import com.epam.ua.trainingProject.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import static com.epam.ua.trainingProject.utils.Constants.*;
import static javax.servlet.http.HttpServletResponse.*;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainController {
    private final UserFacade userFacade;

    @GetMapping("/")
    public String mainPage() {
        return "home";
    }

    @PostMapping("/")
    public String main() {
        return "home";
    }

    @GetMapping("/hello")
    public String helloPage() {
        return "hello";
    }


    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userFacade.activate(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else
            model.addAttribute("message", "Activation code not found");

        return "login";
    }

    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "update user"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("name") String name,
                            @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("classpath:static\\img")));
                stream.write(bytes);
                stream.close();
                return "Вы удачно загрузили " + name + " в " + name + "-uploaded !";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }
    }

    @GetMapping("/admin/page")
    public String adminPage(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String name = auth.getName(); //get logged in username
        model.addAttribute("adminName", principal);

        return "adminPage";
    }

    @GetMapping("/user/page")
    public String userPage(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String name = auth.getName(); //get logged in username
        model.addAttribute("adminName", principal);

        return "userPage";
    }
}
