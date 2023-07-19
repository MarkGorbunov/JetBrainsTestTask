Repository for test task implementation


---  

### Task 1:  

Test cases for task 1 located in testcase/TestCasesForLogin.xlsx file. (Excel)

---  

### Task 2:
Test automation framework created  
Possible Parameters:  
1. env  
    a. local  
    b. dev
2. browser  
    a. chrome
    b. edge
    c. firefox
3. suits (can be found in resources.suits folder, some examples below)  
    a. builds
    b. projects
    c. users
    d. regression  

#### How to Run examples:  

You need specify credentials for admin user in admin_credentials.properties file  
You need specify baseUrl in dev/local.properties file

UI:  
From the root folder - "./gradlew clean -Dsuite=users -Dbrowser=chrome -Denv=local runUiTests"

API:
From the root folder - "./gradlew clean runApiTests"   

  Test Results file/folder:  
  - ui\build\reports\tests\runUiTests\index.html
  - api\build\reports\tests\runUiTests\index.html



---
  
### **Things to discuss:**  
1. Do we need common element for table interaction
2. Do we need human-readable logs, or simple text reporter logs are enough
3. Suits splitting
4. Storing common/separate env/admin files for api/ui modules

---

### _**Bugs**:_  

severity: very low,   
priority: very low  
**Steps to reproduce:**
1. Login as Admin user
2. Open Users tab http://localhost:8112/admin/admin.html?item=users
3. Execute search with any existence user which possible to select(click on checkbox)
4. Click on filter button again    

**Expected result:**  
User actions bottom tab disappear as all selection reset  
**Actual result:**  
User actions bottom tab presented(Add to groups, Remove user)  
on click on Remove nothing will happen