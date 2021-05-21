/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.service.CauseService;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class CauseController {

	private static final String VIEWS_CAUSE_CREATE_OR_UPDATE_FORM = "cause/createCauseForm";

	private final CauseService causeService;
	@Autowired
	public CauseController(CauseService causeService) {
		this.causeService = causeService;

	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}	
	
	@GetMapping(value = "/causes")
	public String showCauseList(Map<String, Object> model) {
		Collection<Cause> causes = causeService.findCauses();
		model.put("causes", causes);
		Cause cause = new Cause(); 
		model.put("cause", cause);
		return "cause/causeList";
	}
	
	@GetMapping(value = "/causes/{causeId}")
	public String showCause(Map<String, Object> model,@PathVariable("causeId") int id) {
		Cause causes = causeService.findCauseById(id);
		model.put("cause", causes);
		return "cause/cause";
	}
	

	@GetMapping(value = "/causes/new")
	public String initCreationForm(Map<String, Object> model) {
		Cause cause = new Cause();
		model.put("cause", cause);
		return VIEWS_CAUSE_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/causes/new")
	public String processCreationForm(@Valid Cause cause, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_CAUSE_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.causeService.saveCause(cause);
			
			return "redirect:/causes";
		}
		
	}
}
