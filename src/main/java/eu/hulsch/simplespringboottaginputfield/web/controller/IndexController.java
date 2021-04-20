package eu.hulsch.simplespringboottaginputfield.web.controller;

import eu.hulsch.simplespringboottaginputfield.web.model.AlertMessage;
import eu.hulsch.simplespringboottaginputfield.web.model.WebModelUserCities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class IndexController {

    private Logger log = LoggerFactory.getLogger(IndexController.class);

    @GetMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getIndexPage(RedirectAttributes redirectAttributes, Model model) {
        if (!model.containsAttribute(WebModelUserCities.WEB_MODEL_USER_CITIES)) {
            model.addAttribute(WebModelUserCities.WEB_MODEL_USER_CITIES, new WebModelUserCities());
        }

        return new ModelAndView("index");
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView postUserCities(@ModelAttribute(name = WebModelUserCities.WEB_MODEL_USER_CITIES)
                                             @Valid WebModelUserCities webModelUserCities,
                                             BindingResult bindingResult,
                                             RedirectAttributes redirAttr)
    {
        if (bindingResult.hasErrors()) {
            log.debug("postUserCities contains {} error(s), returning to {}", bindingResult.getAllErrors().size(), "/");
            redirAttr.addFlashAttribute("org.springframework.validation.BindingResult." + WebModelUserCities.WEB_MODEL_USER_CITIES, bindingResult);
            redirAttr.addFlashAttribute(WebModelUserCities.WEB_MODEL_USER_CITIES, webModelUserCities);
            return new ModelAndView("redirect:" + "/");
        }

        AlertMessage message = AlertMessage.success("Successfully posted user and cities " + webModelUserCities.toString());
        redirAttr.addFlashAttribute(AlertMessage.ALERT_MODEL_NAME, message);

        return new ModelAndView("redirect:" + "/");
    }

}
