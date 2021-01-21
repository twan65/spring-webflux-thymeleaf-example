package org.example.web.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
public class SampleController {

    @GetMapping("/")
    public String index(Model model) {
        Flux<String> flux = Flux
                .range(0, 5)
                .map(i -> "count :" + i)
                .repeat(2)
                .delayElements(Duration.ofSeconds(1L));


        // data streaming, data driven mode.
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(flux, 1);

        model.addAttribute("items", reactiveDataDrivenMode);

        return "index";

    }

    @GetMapping("/hello")
    public String helloWorld(Model model) {

        Mono<String> mono = Mono.just("Hello World!!");
        model.addAttribute("item", mono);

        return "hello";

    }

}
