package in.ashokit.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Citizen {
	
	private Integer citizenId;
	private String citizenName;
	private String gender;
	private String planName;
	private String planStatus;
	private String planStartDate;
	private String planEndDate;
	private Double benefitAmt;
	private String denailReason;
	private String terminatedDate;
	private String terminationReason;
}
