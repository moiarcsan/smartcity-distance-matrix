package com.emergya.smc.dm.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.emergya.smc.dm.DistanceMatrixCell;
import com.emergya.smc.dm.DistanceMatrixHandler;
import com.emergya.smc.dm.webapp.dto.DistanceMatrixRequest;

/**
*
* @author marcos
*/
@RestController
@RequestMapping("/dm")
public class DistanceMatrixController {
	
	@Autowired
	DistanceMatrixHandler dmhandler;
	
	@ResponseBody    
    @RequestMapping(method = RequestMethod.GET)    
    public String test() {
        return "Try using POST on /calculte path";
    }
	
	@ResponseBody
    @RequestMapping(value = "calculateDM", method = RequestMethod.POST)
    public DistanceMatrixCell[][] calculateMTSP(@RequestBody DistanceMatrixRequest request) {
		DistanceMatrixCell[][] matrix = null;
		if(request.getStopsTo().size() != 0){
			matrix = dmhandler.getMatrix(request.getStopsFrom(), request.getStopsTo(), false);
		}else{
			matrix = dmhandler.getMatrix(request.getStopsFrom(), request.getStopsFrom(), false);
		}
        return matrix;
    }
	
	@ResponseBody
    @RequestMapping(value = "calculateDM", method = RequestMethod.GET)
    public ModelAndView calculateDM() {
		ModelAndView model = new ModelAndView("error");
		model.addObject("text", "Try using POST on /calculteDM path");
		return model;
    }

	@ResponseBody
    @RequestMapping(value = "calculateRouteDM", method = RequestMethod.POST)
    public DistanceMatrixCell[][] calculateRouteDM(@RequestBody DistanceMatrixRequest request) {
		DistanceMatrixCell[][] matrix = null;
		if(request.getStopsTo().size() != 0){
			matrix = dmhandler.getMatrix(request.getStopsFrom(), request.getStopsTo(), true);
		}else{
			matrix = dmhandler.getMatrix(request.getStopsFrom(), request.getStopsFrom(), true);
		}
        return matrix;
    }
	
	@ResponseBody
    @RequestMapping(value = "calculateRouteDM", method = RequestMethod.GET)
    public ModelAndView calculateRouteDM() {
		ModelAndView model = new ModelAndView("error");
		model.addObject("text", "Try using POST on /calculteRouteDM path");
		return model;
    }
}
