package in.ashokit.impl;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.model.Citizen;
import in.ashokit.model.SearchRequest;
import in.ashokit.repo.CitizenPlanRepository;
import in.ashokit.service.ReportService;
import in.ashokit.utils.EmailUtils;
import in.ashokit.utils.ExcelGenerator;
import in.ashokit.utils.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository repo;

	@Autowired
	private ExcelGenerator excelGenerator;

	@Autowired
	PdfGenerator pdfGenerator;
	
	@Autowired
	EmailUtils emailUtls;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<String> getPlans() {
		// TODO Auto-generated method stub
		return repo.getPlanNames();
	}

	@Override
	public List<String> getStatus() {
		// TODO Auto-generated method stub
		return repo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> getSearchResults(SearchRequest searchRequest) {
		// TODO Auto-generated method stub
		CitizenPlan entity = new CitizenPlan();

		// BeanUtils.copyProperties(searchRequest, entity);
		if (searchRequest.getPlanName() != null && !searchRequest.getPlanName().equals("")) {
			entity.setPlanName(searchRequest.getPlanName());
		}

		if (searchRequest.getPlanStatus() != null && !searchRequest.getPlanStatus().equals("")) {
			entity.setPlanStatus(searchRequest.getPlanStatus());
		}

		if (searchRequest.getGender() != null && !searchRequest.getGender().equals("")) {
			entity.setGender(searchRequest.getGender());
		}

		if (searchRequest.getStartDate() != null && !searchRequest.getStartDate().equals("")) {
			String startDate = searchRequest.getStartDate();
			DateTimeFormatter farmeter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localStartDate = LocalDate.parse(startDate, farmeter);
			entity.setPlanStartDate(localStartDate);
		}

		if (searchRequest.getEndDate() != null && !searchRequest.getEndDate().equals("")) {
			String endDate = searchRequest.getEndDate();
			DateTimeFormatter farmeter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localEndDate = LocalDate.parse(endDate, farmeter);
			entity.setPlanEndDate(localEndDate);
		}
		return repo.findAll(Example.of(entity));
	}

	@Override
	public Integer saveCitizen(Citizen citizen) {

		CitizenPlan entity = modelMapper.map(citizen, CitizenPlan.class);

		if (citizen.getPlanStartDate() != null && !citizen.getPlanStartDate().equals("")) {
			String startDate = citizen.getPlanStartDate();
			DateTimeFormatter farmeter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localStartDate = LocalDate.parse(startDate, farmeter);
			entity.setPlanStartDate(localStartDate);
		}

		if (citizen.getPlanEndDate() != null && !citizen.getPlanEndDate().equals("")) {
			String endDate = citizen.getPlanEndDate();
			DateTimeFormatter farmeter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localStartDate = LocalDate.parse(endDate, farmeter);
			entity.setPlanEndDate(localStartDate);
		}

		if (citizen.getTerminatedDate() != null && !citizen.getTerminatedDate().equals("")) {
			String termiDate = citizen.getTerminatedDate();
			DateTimeFormatter farmeter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localStartDate = LocalDate.parse(termiDate, farmeter);
			entity.setTerminatedDate(localStartDate);
		}

		Integer citzenId = repo.save(entity).getCitizenId();
		return citzenId;
	}

	@Override
	public List<CitizenPlan> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public void deleteCitizen(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public CitizenPlan findById(Integer id) {
		// TODO Auto-generated method stub
		return repo.getById(id);
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		
		File file = new File("Plans.xls");
		excelGenerator.generate(response, repo.findAll(), file);
		
		System.out.println("--------exportExcel end----------");
		
		emailUtls.sendEmail("Test Mail", "First Mail", "srinidova@gmail.com", file);
		file.delete();
		System.out.println("--------mail sent----------");
		
		
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
	
		System.out.println("--------exportPdf start----------");
		File file = new File("Plans.pdf");
		pdfGenerator.generate(response, repo.findAll(), file);
		
		System.out.println("--------exportPdf end----------");
	
		emailUtls.sendEmail("Test Mail", "First Mail", "srinidova@gmail.com", file);
		file.getName();
		System.out.println("--------mail sent----------");
		return false;
	}

}
