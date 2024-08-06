package com.mercadolivre.ticketmaster.infrastructure.client.users.response;

import com.mercadolivre.ticketmaster.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends User {
}
