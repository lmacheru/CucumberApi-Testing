$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/java/functional/Api_Test.feature");
formatter.feature({
  "name": "end to end tests for Dogs api",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "dog pictures",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "A Random Breed",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinitions.Steps.RandomBreedCheck()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Get list of Random Breed",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinitions.Steps.getAllList()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Check if Bulldog is there",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.Steps.bulldogCheck()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Retrieve all sub-breeds",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinitions.Steps.SubBreeds()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "pet store",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "all available pets Confirm Doggie with Category 12 is in the response",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinitions.Steps.AvailableStatus()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I add a new pet",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinitions.Steps.addPetInList()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The pet is added and ID retrieved",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.Steps.petIsAdded()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Retrieve info of created pet using id",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.Steps.getPetWithId()"
});
formatter.result({
  "status": "passed"
});
});