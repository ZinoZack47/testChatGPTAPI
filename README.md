# Izicap Intern Technical Test Documentation
### Candidate's name: Ziyad RAKIB

## Description
This is a microservice application in which we will be using OpenAI's ChatGPT API to retrieve answers (response) for prompts that we will specifying in our endpoints. Said answers will then be stored in a csv file.

---

## Getting Started
1. The IDE I prefer to use for developing in Java is **JetBrains IntelliJ IDEA Ultimate** (Educational License).
2. I created a new Spring Boot project using the **Spring Initializr** section, setting the type to **Maven** and **Java** to 17 (corretto-17).
3. I checked **Spring Boot Starter** for the annotations that I will be using later on, as well as **Spring Boot Actuator** under DevOPS.
4. I added **springdoc-openapi-starter-webmvc-ui** to pom.xml for openapi **(Swagger)** annotations and UI.

---

## Projet Structure

### Models

This package contains all the entities that will be mapped by its corresponding Json Object counterpart.

- ```Question```
- ```Answer```
- ```Choice```
- ```Usage```
- ```Error```
- ```Records.ErrorRecord```
- ```Response```

**Notes**:
- I made a record for the class ```Error```, it behaves like a wrapper for it, so we can map the error field inside the json object return by ChatGPT's API in case of failure.
- To extract our answer, we simply need to return the choice's text at our first index of choices, we also need to trim it, so we can remove the preceding \n\n and any other unnecessary whitespaces.
- The ```Response``` class purpose is to store an instance ```Answer``` or ```Error``` but not both as one of them will be set to ```null```, it's a one-time assignment through the constructor.

### Services

This package contains the ```ChatGPTService```, this class will serve as our messenger to send requests (question) and receive a response.

**Notes:**
- I implemented the Singleton design pattern for this class since there will be only one instance, in addition to the use of ```@AutoWired``` being discouraged by my IDE for non-testing.

### DB

This package contains the ```CSVWriter``` which implements the ```IDBWriter``` interface in case we wanted to switch the database to MySQL for example (SOLID).

**Note**:
- The CSV file in this case will be stored in the path assigned in the environment variable ```DATA_PATH```, if it's not specified then, the file will be created/operated in the working directory.

### Controllers

This package the controller class that has 2 endpoints:

- A ```@GetMapping``` that requires 2 inputs {question} & {api} (your **prompt** and **ChatGPT API Key** respectively) as path variables, when this endpoint is used, it's going to register the question and answer and return a raw string as a response body.
- A ```@PostMapping``` that requires more details, it requires {api} as a path variable and a request body containing the model, prompt, max_tokens and temperature. As such the response body will be a json object containing the answer's details. 

**Note**: I made sure to document both endpoints as much as I could using **Swagger openapi** for more details. 

---

## Tests

There are 5 tests that were deemed to be necessary:

1. Check if the CSVWriter is working as intended, by checking it's last line.
2. Check to see if the ChatService Answer Json Object is mapped correctly, that the status code is ok (200).
3. Check to see if the ChatService Error Json Object is mapped correctly.
4. In the other class ```TestGetQuestionAnswer``` in which we test our controller, I used **mockMVC** to check that we indeed get the ok status, the answer mapped and registered as intended for our GET endpoint.
5. Same thing as 4. goes for our POST endpoint.
---

## How to run?
In order to run this project inside a docker container, one must follow these steps:
1. Make sure you're using the appropriate jdk (Java 17+), Maven, Docker, an internet browser, and have an internet connection.
2. ```cd``` to the root of this project.
3. Make sure you're using the appropriate jdk (Java 17+) and Maven.
4. Run the command: ```mvn clean package```
![Screenshot](media/mvn-clean-package.png?raw=true "mvn package")
Your target folder should look like this:
![screenshot](media/target.png?raw=true "target")
**Note**: I included ```.idea``` folder as well in this project, maven build, Spring boot run and tests should be able to be ran once setting up the jdk in your IntelliJ IDEA.
5. Run the command: ```docker build -t ask-chatgpt-izicap .```
![Screenshot](media/docker-build.png?raw=true "docker build")
We can check if the image was successfully built using the command ```docker images```
![Screenshot](media/docker-images.png?raw=true "docker images")
6. Run the docker image using the following command: ```docker run -it -p 8080:8080 -v ~/Dropbox/Documents/data:/data -e DATA_PATH=/data ask-chatgpt-izicap```.
Change ```~/Dropbox/Documents/data``` to the full path in which you want to store the .csv file in the local drive.
Also, if the port 8080 is unavailable or busy you can change the port mapping to something like ```-p 8081:8080``` instead.
![Screenshot](media/docker-run.png?raw=true "docker images")
7. Open your browser and go to http://localhost:8080/swagger-ui/index.html (in my case)
![Screenshot](media/swagger-ui.png?raw=true "Swagger UI")
- **Quick Ask**:
![Screenshot](media/swagger-ui-quick-ask.png?raw=true "Swagger UI Quick Ask")
After filling both fields and hitting execute:
![Screenshot](media/swagger-ui-quick-ask-execute.png?raw=true "Swagger UI Quick Ask Execute")
- **Full Ask**:
![Screenshot](media/swagger-ui-full-ask.png?raw=true "Swagger UI Full Ask")
After filling all fields:
![Screenshot](media/swagger-ui-full-ask-fields.png?raw=true "Swagger UI Full Ask Fields")
Hit Execute and here are the results:
![Screenshot](media/swagger-ui-full-ask-execute.png?raw=true "Swagger UI Full Ask Execute")
- **The CSV File**:
![Screenshot](media/folder.png?raw=true "Folder")
**Content**:
![Screenshot](media/cat-file.png?raw=true "Content")
**Health**:
![Screenshot](media/health.png?raw=true "Health")