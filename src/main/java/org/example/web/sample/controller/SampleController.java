package org.example.web.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SampleController {

    @GetMapping("/")
    public String index(Model model) {
        Flux<String> flux = Flux
                .range(0, 5)
                .map(i -> "count :" + i)
                .repeat(10)
                .delayElements(Duration.ofSeconds(1L));


        // data streaming, data driven mode.
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(flux, 1);

        model.addAttribute("items", reactiveDataDrivenMode);

        return "index";

    }

}
