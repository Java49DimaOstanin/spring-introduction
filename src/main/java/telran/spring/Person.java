package telran.spring;

import jakarta.validation.constraints.*;

public record Person(long id,@Pattern (regexp = "[A-Z][a-z]{2,}")String name,
		@NotEmpty String city, /*TODO validation */String email,/*TODO israel phone valedetion*/ String phone) {

}
