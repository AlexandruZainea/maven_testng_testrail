import com.jayway.restassured.RestAssured;
import org.testng.IInvokedMethod;
import org.testng.IReporter;
import org.testng.ISuite;

import java.lang.annotation.Annotation;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class ReporterListener implements IReporter {

    @Override
    public void generateReport(List xmlSuites, List suites, String outputDirectory) {


        ISuite suite = (ISuite) suites.get(0);
        List<IInvokedMethod> allMethods = suite.getAllInvokedMethods();

        for (IInvokedMethod method : allMethods) {
            Class obj = method.getTestMethod().getRealClass();
            Annotation annotation = null;
            try {
                annotation = obj.getDeclaredMethod(method.getTestMethod().getMethodName()).getAnnotation(TestCase.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            TestCase testerInfo = (TestCase) annotation;
            System.out.printf("ANNOTATION: " + testerInfo.id());

        }
    }


    private void loginToTestRail(){
        RestAssured.baseURI = "https://hilleltest2.testrail.net:8080";
        String body = "{\n" +
                "    \"username\": \"test\",\n" +
                "    \"password\": \"test)\"\n" +
                "} ";

      String  coockie = given().
                header("Content-Type", "application/json").
                body(body).
                when().
                post("/rest/auth/1/session").
                then().
                extract().
                path("session.value");
    }

}