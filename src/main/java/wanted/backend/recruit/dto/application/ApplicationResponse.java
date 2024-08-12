package wanted.backend.recruit.dto.application;

import lombok.Getter;
import wanted.backend.recruit.entity.Application;

@Getter
public class ApplicationResponse {
    private Long id;
    private Long user_id;

    public ApplicationResponse(Application application) {
        this.id = application.getId();
        this.user_id = application.getUser().getId();
    }
}
