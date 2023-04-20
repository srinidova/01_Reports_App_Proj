package in.ashokit.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class SearchRequest {

	private String planName;
	private String planStatus;
	private String gender;
	private String startDate;
	private String endDate;
}
