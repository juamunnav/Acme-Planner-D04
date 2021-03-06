
package acme.features.administrator.spam;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Spam;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorUpdateThresholdService implements AbstractUpdateService<Administrator, Spam> {

	@Autowired
	protected AdministratorSpamRepository repository;


	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "threshold");

	}

	@Override
	public Spam findOne(final Request<Spam> request) {
		assert request != null;

		final List<Spam> l = this.repository.findMany().stream().collect(Collectors.toList());
		return l.get(0);
	}

	@Override
	public void validate(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<Spam> request, final Spam entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
