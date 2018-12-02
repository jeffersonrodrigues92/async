package br.com.spring.async.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;


@RestController
@RequestMapping("/")
public class AsyncController {

    @Async
    @GetMapping("/async")
    public void asyncVoidMethod() {
        System.out.println("Executando método assincrono:  " + Thread.currentThread().getName());
    }

    @GetMapping("/sincro")
    public String sincMethodWithReturnType() {
        System.out.println("Execute metodo sincrono"
                + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            return  "hello world !!!!";
        } catch (InterruptedException e) {
            //
        }

        return null;
    }

    @Async
    @GetMapping("/future")
    public Future<String> asyncMethodWithReturnType() {
        System.out.println("Execute method asynchronously - "
                + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            System.out.println("Response method asyncronous");
            return new AsyncResult<>("hello world !!!!");
        } catch (InterruptedException e) {
            //
        }

        return null;
    }

    @Async("threadPoolTaskExecutor")
    @GetMapping("/executor")
    public void asyncMethodWithConfiguredExecutor() {
        System.out.println("Execute method with configured executor - "
                + Thread.currentThread().getName());
    }
}
