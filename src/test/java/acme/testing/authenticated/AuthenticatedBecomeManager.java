
package acme.testing.authenticated;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.SignUpTest;

public class AuthenticatedBecomeManager extends SignUpTest {

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

		super.setBaseCamp("http", "localhost", "8080", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");

		this.navigateHome();
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Populate DB (samples)");

	}
	/*
	 * Tested Feature: An authenticated user becomes a manager
	 * Violated constraints: Not applicable.
	 * Expected results: An authenticated becomes a manager
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/becomeManagerPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void testBecomeManagerPositive(final String company, final String sector) {

		super.signIn("authenticated01", "authenticated01");
		super.clickOnMenu("Account", "Become a manager");

		super.fillInputBoxIn("company", company); 
		super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Register");
		super.checkLinkExists("Manager");
		super.signOut();

	}
	
	/*
	 * Tested Feature: An authenticated user becomes a manager
	 * Violated constraints: Company and sector must not be blank.
	 * Expected results: An authenticated does not become a manager due to the errors at the form
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/becomeManagerNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void testBecomeManagerNegative(final String company, final String sector) {

		super.signIn("authenticated02", "authenticated02");
		super.clickOnMenu("Account", "Become a manager");

		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Register");
		super.checkErrorsExist();
		super.signOut();

	}

}
