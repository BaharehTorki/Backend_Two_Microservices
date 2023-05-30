package se.nackademin.service;

import se.nackademin.Model.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Value("${ITEM_HOST}")
    private String host;
    @Value("${ITEM_BASE_URL}")
    private String baseUrl;

    public List<Item> getAllItems() {
        RestTemplate restTemplate = new RestTemplate();
        Item[] objects = restTemplate.getForObject(host.concat(baseUrl).concat("/all"), Item[].class);
        return Arrays.stream(objects).collect(Collectors.toList());
    }

    public Item getItemById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(host.concat(baseUrl).concat(id.toString()), Item.class);
    }
}
