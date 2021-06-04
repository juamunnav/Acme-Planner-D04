package acme.testing.manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.SignUpTest;

public class ManagerUpdateTest extends SignUpTest {
	
	
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
	 * Tested Feature: A Manager user updates sector and company fields
	 * Violated constraints: Not applicable.
	 * Expected results: A Manager user updates sector and company fields
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/becomeManagerPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void testUpdateManagerPositive(final String company, final String sector) {

		super.signIn("manager01", "manager01");
		super.clickOnMenu("Account", "Manager data");

		super.fillInputBoxIn("company", company); 
		super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Update");
		super.checkLinkExists("Manager");
		super.signOut();

	}
	
	/*
	 * Tested Feature: A Manager user updates sector and company fields
	 * Violated constraints: sector and company fields blank.
	 * Expected results: Errors messages in sector and company fields
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/becomeManagerNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void testUpdateManagerNegative(final String company, final String sector) {

		super.signIn("manager01", "manager01");
		super.clickOnMenu("Account", "Manager data");

		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		super.clickOnSubmitButton("Update");
		super.checkErrorsExist();
		super.signOut();

	}

}
