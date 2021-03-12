package me.tereshko.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("product")
public interface ProductClient {

    @GetMapping("/{id}")
    String parametrized(@PathVariable(value = "id") String id);

    @GetMapping()
    String getPage(@RequestParam MultiValueMap<String, String> params,
                   @RequestParam(defaultValue = "0") String page,
                   @RequestParam(defaultValue = "3") String size);

}