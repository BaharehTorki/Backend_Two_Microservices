package se.nackademin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.nackademin.Model.Order;
import se.nackademin.Model.OrderList;
import se.nackademin.Model.Purchases;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    private ItemService itemService;
    private CustomerService customerService;

    @Autowired
    public OrderService(ItemService itemService, CustomerService customerService) {
        this.itemService = itemService;
        this.customerService = customerService;
    }

    @Value("${PURCHASE_HOST}")
    private String host;
    @Value("${PURCHASE_BASE_URL}")
    private String baseUrl;

    public OrderList getAllOrder(Long customerID)  {
//        URI url = getUrl(customerID.toString());
        RestTemplate restTemplate = new RestTemplate();
        Purchases[] purchases = restTemplate.getForObject( host + baseUrl +customerID.toString(), Purchases[].class);
        if (purchases != null) {
            return OrderList.builder()
                    .customer(Arrays.stream(purchases)
                            .findFirst()
                            .map(purchase -> customerService.findCustomerById(purchase.getCustomerId())).orElse(null))
                    .orderList(Arrays.stream(purchases).map(this::map).collect(Collectors.toList()))
                    .build();
        }
        return null;
    }

    public void addOrder(Purchases purchases) {
//        URI addUri = getUrl("add");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity( host + baseUrl +"add", purchases, Purchases.class);
    }

   /* private URI getUrl(String pathVariable) throws UrlException {
        try {
            return new URI(host + baseUrl + pathVariable);
        } catch (URISyntaxException e) {
            log.error("The url={} is not reachable", host + baseUrl + pathVariable);
            throw new UrlException(e.getMessage());
        }
    }*/

    private Order map(Purchases purchases) {
        return (Objects.nonNull(purchases)) ?
                Order.builder()
                        .id(purchases.getId())
                        .date(purchases.getDate())
                        .itemList(
                                (Objects.nonNull(purchases.getItems())) ?
                                        purchases.getItems().stream()
                                                .filter(Objects::nonNull)
                                                .map(item -> itemService.getItemById(item))
                                                .collect(Collectors.toList())
                                        : null)
                        .build()
                : null;
    }
}
