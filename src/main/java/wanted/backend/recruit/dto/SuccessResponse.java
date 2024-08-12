package wanted.backend.recruit.dto;

import lombok.Getter;

@Getter
public class SuccessResponse {
    private boolean success;

    public SuccessResponse(boolean success) {
        this.success = success;
    }
}
