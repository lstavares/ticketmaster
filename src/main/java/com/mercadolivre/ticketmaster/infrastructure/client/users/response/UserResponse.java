package com.mercadolivre.ticketmaster.infrastructure.client.users.response;

import com.mercadolivre.ticketmaster.domain.dto.UserDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class UserResponse extends UserDTO {
}
