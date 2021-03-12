package me.tereshko.feign.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductClient productClient;


    @GetMapping("/get/{id}")
    public String getById(@PathVariable String id) {
        String answer = productClient.parametrized(id);
        return answer;
    }

    @GetMapping()
    public String getAllProducts() {
        String answer = productClient.getPage(null, "1", "4");
        return answer;
    }
}