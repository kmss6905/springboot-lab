package com.example.springmvcfirst.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MappingController {

    @RequestMapping({"/hello-basic", "/hello-go"})
    public String helloBasic() {
        log.info("hello-basic");
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode"
     * params="!mode"
     * params="mode=debug"
     * params="mode!= debug"
     * params={"mode=debug", "data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     */
    @GetMapping(value = "/mapping-header", params = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Meaia Type
     * consumes = "application/json"
     */
    @GetMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingContentType() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Tpye
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @GetMapping(value = "/mapping-produce", consumes = "application/json")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
