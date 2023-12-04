// BEGIN
package exercise.dto;

import exercise.util.FlashEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePage {
    private String flash;
    private FlashEnum flashType;
}

// END
