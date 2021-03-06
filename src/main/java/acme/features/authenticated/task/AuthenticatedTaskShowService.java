package acme.features.authenticated.task;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import acme.entities.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedTaskShowService implements AbstractShowService<Authenticated, Task> {

	@Autowired
	protected AuthenticatedTaskRepository repository;
	
	@Override
	public boolean authorise(final Request<Task> request) {

		assert request != null;
		
		return true;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model!= null;
		
		request.unbind(entity, model,  "start", "end", "title", "text", "link","visibility","workLoad");
		
	}

	
	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;
		Task result;
		Integer id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOnebyId(id);
		Assert.state(result.getEnd().before(Date.from(Instant.now())) && result.getVisibility(), "default.error.not-authorised");

		return result;
	}

}
