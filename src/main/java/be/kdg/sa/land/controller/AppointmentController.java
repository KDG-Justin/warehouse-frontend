package be.kdg.sa.land.controller;

import be.kdg.sa.land.controller.dto.AppointmentDto;
import be.kdg.sa.land.domain.enums.MaterialType;
import be.kdg.sa.land.exception.TruckException;
import be.kdg.sa.land.service.appointment.FindAppointmentsService;
import be.kdg.sa.land.service.appointment.CreateAppointmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;



@Controller
public class AppointmentController {
    private final FindAppointmentsService findAppointmentsService;
    private final CreateAppointmentService createAppointmentService;

    public AppointmentController(FindAppointmentsService findAppointmentsService, CreateAppointmentService createAppointmentService) {
        this.findAppointmentsService = findAppointmentsService;
        this.createAppointmentService = createAppointmentService;
    }

    @GetMapping("/appointments/{id}")
    public String getAppointment(@PathVariable UUID id, Model model) {
        try{
            AppointmentDto appointmentDto = findAppointmentsService.findAppointmentBySellerId(id);
            model.addAttribute("appointmentDto", appointmentDto);
            return "appointment";

        } catch (TruckException e) {
            throw new TruckException(String.format("Truck not found. %s ", e));
        }
    }

    @GetMapping("/appointments")
    public ModelAndView getAppointments(Model model) {
        ModelAndView modelAndView = new ModelAndView("appointments");
        Collection<AppointmentDto> appointmentDtoCollection = findAppointmentsService.findAllAppointments();
        modelAndView.addObject("appointmentDtoCollection", appointmentDtoCollection);

        return modelAndView;
    }

    @GetMapping("/appointments-form")
    public String addAppointment(Model model) {
        model.addAttribute("newAppointment", new AppointmentDto());
        model.addAttribute("materialTypes", MaterialType.values());
        return "appointmentForm";
    }

    @PostMapping("/appointments")
    public String addAppointment(@ModelAttribute("newAppointment") @Valid AppointmentDto appointmentDto, BindingResult errors){
        if(errors.hasErrors()){
            return "appointmentForm";
        }

        createAppointmentService.createAppointment(appointmentDto);

        return "redirect:/appointments";
    }
}
