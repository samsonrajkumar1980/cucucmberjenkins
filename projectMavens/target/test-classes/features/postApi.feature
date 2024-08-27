Feature:Create Post API and validate the response status  

#@Regression
Scenario:Create Post API 
Given pass the prerequest data in post api along with request 
When execute the POST API
Then should return 201 response code
And verify the "job" id is matching with exepected result
And get the Job id from post api response

@Regression
Scenario:Get Post API 
Given pass the prerequest data in post api along with request 
When execute the GET API
Then should return 200 response code
And get the year 2003  name has to  matching with "aqua sky"exepected result




