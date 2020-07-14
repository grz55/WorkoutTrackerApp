package com.grz55.workouttracker.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WorkoutTrackerErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String errorHandler(HttpServletRequest request, Model model) {

        String errorPage = "error/500";
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value())
                errorPage = "error/404";
            if (statusCode == HttpStatus.FORBIDDEN.value())
                errorPage = "error/403";
            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
                errorPage = "error/500";
        }

        return errorPage;

    }
}
