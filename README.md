# **High Level Design for SplitWise**
## Functional Requirements
~ Users Can be sign up/ sign in to your system
~ Users can add thier contacts in the system by email or phone
~ Users can add Expense in the System with multiple contacts
~ Users can create groups and can add group expense with the group members
~ System has to show balance report of all users
~ System can show balance report of particular User
~ System can show balance report of all and particular group 

## Non Functional Requirements
~The system should be highly available.Because if the service is down , no user will be able to access it.

## Extended Requirements
~System can simplyfy debts
~System can show passbook to user.The entries should show all the transactions a user was part of

## Capacity Estimation
Read will be more than writes in this system.Lets assume if each user has 20 contacts . So read vs write ratio will be 20:1.
## Traffic estimates
Lets assume we have 20M new users per month with 20:1 read and write then we can expect 20*20M =>400M read
### Queries per Second (QPS) will be 20M / (30 days * 24 hours * 3600 sec) ~= 7.71 write/sec
### Consedering 20:1 read to write request 20 * 7.71 ~= 154.2 read/sec
## Storage Estimates
Lets assume we are saving the expense over the year of 5 years then 20M write request every month, total number of obects we 
expect to store will be : 20M * 5years * 30 months ~= 3Billion
if each object stored take 500 Byte . Total storage we need is ~= 3B * 500 ~= 1.5TB

## BandWidth estimates 
For Write Request
Since there are 7.7 write request every sec , so total incoming data for our service will be : 7.7 * 500Bytes =~ 3.75 Kb/sec
For Read Request
Since there are 154.2 read request every sec , so total outgoing data for our service will be : 154.2 * 500Bytes =~ 75.29 Kb/sec 

## Basic System Design 

file:///home/ins581/Downloads/SplitWise_HLD.png

# mock-machine-coding-2
Welcome to the 2nd Mock Machine Coding Round by [workat.tech](http://workat.tech).

Please visit [this link](https://workat.tech/machine-coding/practice/splitwise-problem-0kp2yneec2q2) to participate.

