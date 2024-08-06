package com.mercadolivre.ticketmaster.infrastructure.client.users;

import com.mercadolivre.ticketmaster.infrastructure.client.users.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usersClient", url = "${api.users.url}")
public interface UsersClient {

    @GetMapping("/users/{id}")
    UserResponse getUser(@PathVariable Long id);

}
