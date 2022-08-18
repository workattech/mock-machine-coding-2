package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddUserResponse {

    private Integer status;

    private String message;
}
