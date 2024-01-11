package exercise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Setter
@Getter
public class GuestCreateDTO {

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "\\+(\\d){11,13}")
    private String phoneNumber;

    @NotNull
    @Size(min = 4, max = 4)
    private String clubCard;

    @NotNull
    @Future
    private LocalDate cardValidUntil;
}

// END
