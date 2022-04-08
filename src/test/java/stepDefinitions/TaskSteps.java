package stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

import static org.junit.Assert.*;

public class TaskSteps {
    private WebDriver driver;
    String base_url = "https://kristinek.github.io/site/tasks/list_of_people_with_jobs.html";
    String add_url = "https://kristinek.github.io/site/tasks/enter_a_new_person_with_a_job.html";

    public TaskSteps() {
        this.driver = Hooks.driver;
    }


    @Given("^I am on People with jobs page$")
    public void iAmOnPeopleJobsPage() throws Throwable {
        driver.get(base_url);
    }

    @Given("^There are (\\d+) people on the original list$")
    public void peopleStartList(int listSize) throws Throwable {


        List<String> origNames = new ArrayList<String>(Arrays.asList("Mike", "Jill", "Jane"));
        List<String> origJobs = new ArrayList<String>(Arrays.asList("Web Designer", "Support", "Accountant"));

        List<String> actualNames = new ArrayList<String>();
        List<WebElement> testNames = driver.findElements(By.className("name"));
        for (WebElement e : testNames) {
            actualNames.add(e.getText());
        }
        assertEquals(origNames.size(), actualNames.size());
        assertEquals(origNames, actualNames);
        assertEquals(listSize, actualNames.size());


        List<String> actualJobs = new ArrayList<String>();
        List<WebElement> testJobs = driver.findElements(By.className("job"));
        for (WebElement t : testJobs) {
            actualJobs.add(t.getText());
        }

        assertEquals(origJobs.size(), actualJobs.size());
        assertEquals(origJobs, actualJobs);
        assertEquals(listSize, actualJobs.size());

    }


    @When("^I click on Add person button$")
    public void iClickAddPerson() throws Throwable {
        driver.findElement(By.xpath("//*[@onclick='openModalForAddPersonWithJob()']")).click();
    }

    @And("^I am sent to enter a new person page$")
    public void iAmOnAddPeopleJobsPage() throws Throwable {
        assertEquals(driver.getCurrentUrl(), add_url);

    }

    @And("^I enter name of a new person: \"([^\"]*)\"$")
    public void enterJobPersonName(String name) throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @And("^I enter job of a new person: \"([^\"]*)\"$")
    public void enterJobPersonJob(String job) throws Throwable {
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }

    @And("^I click on Add$")
    public void clickAddInAddPersonPage() throws Throwable {
        driver.findElement(By.xpath("//*[@onclick='addPersonWithJobToList()']")).click();
    }

    @And("^I am set back to home page$")
    public void iGoBackToPeopleJobsPage() throws Throwable {
        assertEquals(driver.getCurrentUrl(), base_url);

    }

    @Then("^There are (\\d+) people on the list now$")
    public void peopleAddedList(int people) throws Throwable {


        List<String> actual = new ArrayList<String>();

        List<WebElement> testNames = driver.findElements(By.className("name"));
        for (WebElement e : testNames) {
            actual.add(e.getText());
        }

        assertEquals(people, actual.size());


    }

    @Then("^Person is added to the list with name \"([^\"]*)\" and job title \"([^\"]*)\"$")
    public void newPersonAdded(String name, String job) throws Throwable {

        List<String> actualNames = new ArrayList<String>();
        List<WebElement> testNames = driver.findElements(By.className("name"));
        for (WebElement e : testNames) {
            actualNames.add(e.getText());
        }
        assertTrue(actualNames.contains(name));

        List<String> actualJob = new ArrayList<String>();
        List<WebElement> testJob = driver.findElements(By.className("job"));
        for (WebElement e : testJob) {
            actualJob.add(e.getText());
        }
        assertTrue(actualJob.contains(job));

    }


    @And("^I click edit button next to Mike$")
    public void clickOnEditMike() throws Throwable {
        driver.findElement(By.xpath("//*[@onclick='openModalForEditPersonWithJob(0)']")).click();
    }

    @And("^I am sent to page for editing Mikes profile$")
    public void goToEditMike() throws Throwable {
        String mikesUrl = "https://kristinek.github.io/site/tasks/enter_a_new_person_with_a_job.html?id=0";
        assertEquals(driver.getCurrentUrl(), mikesUrl);
    }

    @When("^I can change name and job:$")
    public void changeMikesProfile(Map<String, String> valuesForMike) throws Throwable {

        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(valuesForMike.get("name"));

        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(valuesForMike.get("job"));
    }

    @And("^I can click edit to save changes$")
    public void saveChangesOnMike() throws Throwable {
        driver.findElement(By.xpath("//*[@onclick='editPersonWithJob(0)']")).click();
    }

    @And("^the list now has the edited name and job:$")
    public void listWithNewMike(Map<String, String> valuesForNewMike) throws Throwable {

        assertEquals(valuesForNewMike.get("name"), driver.findElement(By.cssSelector("#person0 > .name")).getText());
        assertEquals(valuesForNewMike.get("job"), driver.findElement(By.cssSelector("#person0 > .job")).getText());

    }


    @And("^I click delete button next to Jill")
    public void clickOnRemoveJill() throws Throwable {
        driver.findElement(By.xpath("//*[@onclick='deletePerson(1)']")).click();
    }


    @Then("^There are (\\d+) people on the list$")
    public void peopleRemovedList(int people) throws Throwable {

        List<String> actual = new ArrayList<String>();

        List<WebElement> testNames = driver.findElements(By.className("name"));
        for (WebElement e : testNames) {
            actual.add(e.getText());
        }

        assertEquals(people, actual.size());

    }


    @And("^\"([^\"]*)\" is not on the list$")
    public void iSeeNoJill(String name) throws Throwable {

        List<WebElement> testNames = driver.findElements(By.className("name"));
        for (WebElement testElement : testNames) {
            if (testElement.getText().contains(name)) {
                fail();
            }
        }

    }

    @Given("^I am on a new person page$")
    public void goToAddPeopleJobsPage() throws Throwable {
        driver.get(add_url);
    }

    @And("^I enter name and job:$")
    public void iEnterNewPerson(Map<String, String> valuesForMike) throws Throwable {

        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(valuesForMike.get("name"));

        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(valuesForMike.get("job"));

    }

    @When("^I click on Clear all fields")
    public void clickOnClearFields() throws Throwable {
        driver.findElement(By.id("addPersonBtn")).click();
    }

    @Then("^Name and job fields are cleared and empty$")
    public void formIsCleared() throws Throwable {
        assertEquals("", driver.findElement(By.id("name")).getAttribute("value"));
        assertEquals("", driver.findElement(By.id("job")).getAttribute("value"));
    }

    @And("^I click to Add$")
    public void clickToAddPersonPageNew() throws Throwable {
        driver.findElement(By.id("modal_button")).click();
    }


    @And("^I click on Reset list")
    public void clickOnResetList() throws Throwable {
        driver.findElement(By.xpath("//*[@onclick='resetListOfPeople()']")).click();

    }


    @Then("^List is set back to original$")
    public void resetListToOriginal() throws Throwable {

        List<String> origNames = new ArrayList<String>(Arrays.asList("Mike", "Jill", "Jane"));
        List<String> origJobs = new ArrayList<String>(Arrays.asList("Web Designer", "Support", "Accountant"));

        List<String> actualNames = new ArrayList<String>();
        List<WebElement> testNames = driver.findElements(By.className("name"));
        for (WebElement e : testNames) {
            actualNames.add(e.getText());
        }
        assertEquals(origNames, actualNames);


        List<String> actualJobs = new ArrayList<String>();
        List<WebElement> testJobs = driver.findElements(By.className("job"));
        for (WebElement t : testJobs) {
            actualJobs.add(t.getText());
        }
        assertEquals(origJobs, actualJobs);

    }
}













