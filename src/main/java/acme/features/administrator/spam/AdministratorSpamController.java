
package acme.features.administrator.spam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.Spam;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/spam/")
public class AdministratorSpamController extends AbstractController<Administrator, Spam> {
	
	@Autowired
	AdministratorSpamUpdateService updateService;
	
	@Autowired
	AdministratorUpdateRemoveService updateRemoveService;
	
	@Autowired
	AdministratorUpdateThresholdService updateThresholdService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addCustomCommand(CustomCommand.SPAM_REMOVE_WORD, BasicCommand.UPDATE, this.updateRemoveService);
		super.addCustomCommand(CustomCommand.UPDATE_THRESHOLD, BasicCommand.UPDATE, this.updateThresholdService);

		
	}
}
