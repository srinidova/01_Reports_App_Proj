package in.ashokit.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.model.Citizen;
import in.ashokit.model.SearchRequest;


public interface ReportService {
	
	List<String> getPlans();
	List <String> getStatus();
	List<CitizenPlan> getSearchResults(SearchRequest searchRequest);
	Integer saveCitizen(Citizen citizen);
	List<CitizenPlan> getAll();
	void deleteCitizen(Integer id);
	CitizenPlan findById(Integer id);
	boolean exportExcel(HttpServletResponse response) throws Exception;
	boolean exportPdf(HttpServletResponse response) throws Exception;

}
