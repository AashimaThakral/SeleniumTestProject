package commonComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetry implements IRetryAnalyzer{

    int count = 0;
    int maxRetry = 1;
    public boolean retry(ITestResult result) {
        
        if(count < maxRetry){
            count ++;
            return true;
        }
        return false;
    }
    
}
