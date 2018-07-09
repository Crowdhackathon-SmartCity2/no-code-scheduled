# Optain

A paper shredder.
The Optain is an expansion for the eIDAS system working with the technology of Blockchain.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

To run the project it is necessary to have an Java IDE environment.
One of them can be found below.

```
https://www.eclipse.org/
```

### Installing

A step by step series of examples that tell you how to get a development env running
*Examples are given with the Eclipse environment*

Clone the Repository

```
$ git clone https://github.com/Crowdhackathon-SmartCity2/no-code-scheduled
```
Import the "Blockchain Server" folder to your IDE.

```
General > Import from File System
Browse (Select the folder)
Finish
```
**Notice that Genesis Block of the system includes the information of the Developer**

## Running the tests

The Clients are automatically testing the first User of the Blockchain (that is already registered) after the Genesis Block due to lack of eIDAS authorization.
The User informations are empty.
**Notice that once you enter the information and Accept them from the Submission System the Information became permanent on the blockchain and are not a subject of any change.**

### Starting the server

The main.java is the executable file of the server.

```
Blockchain Server> src/main/java > (default package) > main.java 
```
Then Debug or Run the java file.

### Updating the User informations

The User informations can be updated from the End User App that can be found at:

```
Blockchain Server> src/main/java > User > UI.java 
```
From this app is able to Submit the Income of the user and the Driving License.
The other categories works as a mock up.

### Testing for informations in the Blockchain

The Validation is able from the Businesses App that can be found at:

```
Blockchain Server> src/main/java > Buissnes > UI.java 
```
From this app is able to Validate the Income of the user and the Driving License.
The other validations works as a mock up.

## Additonal Informations

### What is working

* Posting-register user data on the blockchain.
* Register information for user in the End User App on categories of driving license and income.
* Validate user data on any category from the Business registered on the system (Driving License & Income).
* Validating a User is based on the first user registered in the system.
* eIDAS ready. The fields of Server side and Client side are modified to validate.

### Necessary code for the completion of the project

* The database system is just a concept and it will be transferred to a DLT system. 
* Date and time of transaction in the block.
* Registering the rest categories from the End User App and Business in the need of customers.
* Integrate the eIDAS with the release documentation.
* Longing in information is automatically presented due to lack of the eIDAS logging information, the login information is being appeared in the UI.java of each app and being encrypted* with the launch of code

### Enabling Multi Node 

We think about a DLT with Hyperledger. The changes have to made is the connection between the the Server side App and one Client Side App

## Consensus

The user’s data are submitted to the submission system in order to be authenticated only once as a type of information. If the request become accepted then with the unique hash ID (differential with block hash) the information-certificate is being added to the user account or else the information is being deleted. This process is estimated for the scale of Greece to be done within one business day after the initial run. Within the day, the information is being verified and at the night, where there is low demand on the servers, the information is being delivered to the nodes.

## Built With

* [Apache Codec](https://commons.apache.org/proper/commons-codec/download_codec.cgi) - Hashing Algorythm
* [Apache Lang](https://commons.apache.org/proper/commons-lang/) - Turn everything into serialized bytes
* [Maven](https://maven.apache.org/) - Dependency Management - *http*

## Team

* **George Zaimis** - *Developer* - [Facebook](https://www.facebook.com/geoza2000)
* **Fotini Nikidi** - *PR and Marketing* - [Facebook](https://www.facebook.com/fotini.nikidi.11)

