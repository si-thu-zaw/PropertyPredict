package com.team7.propertypredict.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.team7.propertypredict.model.Project;
import com.team7.propertypredict.model.ProjectDetails;
import com.team7.propertypredict.repository.ProjectRepository;

@Component
public class ProjectServiceImpl implements ProjectService{

	@Resource
	private ProjectRepository pRepo;
	
	@Transactional
	public ArrayList<Project> findProjectsByStreet(String street){
		
		ArrayList<Project> projects = pRepo.findProjectsByStreet(street);
		return projects;
	}
	
	@Transactional 
	public ProjectDetails getProjectDetails(Integer pid) {
		ProjectDetails pd = new ProjectDetails();
		
		Project project = findProjectById(pid);
		Integer min = findMinAreaByProjectId(pid).intValue();
		Integer max = findMaxAreaByProjectId(pid).intValue();
		ArrayList<String> floors = findfloorRangeByProjectId(pid);

		Locale usa = new Locale("en", "US");
		NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);
		String averagePrice = dollarFormat.format(findAveragePriceByProjectId(pid));
		
		Integer top = 0;
		for(String floor:floors) {
			if(floor.length()==5) {
				String f = floor.substring(3, 5);
				Integer t = Integer.parseInt(f);
				if(t>top) {
					top = t;
				}
			}
		}
		String topFloor ="";
		if(top==0) {
			topFloor = "-";
		}
		else if(top/10==0) {
			topFloor = "01-0"+top;
		}
		else {
			topFloor = top.toString();
		}
		
		pd.setName(project.getName());
		pd.setStreet(project.getStreet());
		pd.setAveragePrice(averagePrice);
		pd.setTenure(findTenureByProjectId(pid));
		pd.setArea(min + "-" + max + " (square metre)");
		pd.setFloorRange(topFloor);
		return pd;
	}
	
	@Transactional
	public Project findProjectById(Integer pid) {
		Project project = pRepo.findProjectById(pid);
		return project;
	}
	
	@Transactional
	public Double findAveragePriceByProjectId(Integer pid) {
		Double avgPrice = pRepo.findAveragePriceByProjectId(pid);
		return avgPrice;
	}
	
	@Transactional
	public Integer findTotalUnitsByProjectId(Integer pid) {
		Integer units = pRepo.findTotalUnitsByProjectId(pid);
		return units;
	}
	
	@Transactional
	public 	String findTenureByProjectId(Integer pid) {
		String tenure = pRepo.findTenureByProjectId(pid);
		return tenure;
	}
	
	@Transactional
	public 	String findSaleTypeByProjectId(Integer pid){
		String type = pRepo.findSaleTypeByProjectId(pid);
		return type;
	}
	
	@Transactional
	public Double findMinAreaByProjectId(Integer pid) {
		Double min = pRepo.findMinAreaByProjectId(pid);
		return min;
	}
	
	@Transactional
	public Double findMaxAreaByProjectId(Integer pid) {
		Double max = pRepo.findMaxAreaByProjectId(pid);
		return max;
	}
	
	@Transactional
	public ArrayList<String> findfloorRangeByProjectId(Integer pid){
		ArrayList<String> floors = pRepo.findfloorRangeByProjectId(pid);
		return floors;
	}
	

	

}
