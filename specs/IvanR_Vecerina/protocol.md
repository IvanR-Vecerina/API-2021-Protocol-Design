# **Protocol Specifications**
## **Objectives:**
We want to allow a client to ask our server to compute a calculation and return the result. The server should be able to compute basic operations like addition and multiplication. A list for the available  operations should be provided when the client connects.

## **Overall Behavior:**
### <u>Transport protocol choice</u>
We shall use TCP/IP as transportation protocol as it is the only one we know.

### <u>How does the client find the server (addresses and ports)?</u>
The IP address MUST be known and the port number must be the one defined on protocol creation for the client to connect to the server. The connection made, the server responds with a list of valid operations.

### <u>Who speaks first?</u>
When the client wants to compute an arithmetic operation he sends an OPERATION followed by two OPERANDS. If the request is invalid the server responds with  an ERROR indicating what went wrong. In the case of a valid request, responds with a RESULT.

### <u>Who closes the connection and when?</u>
The connection is kept opened until the client sends a GOODBYE_MY_LOVER request to which the server responds with a GOODBYE_MY_FRIEND result. 

## **Messages:**
### <u>What is the syntax of the messages?</u>
Correct syntax : OPERATION op1 op2

### <u>What is the sequence of messages exchanged by the client and the server? (flow)</u>
The client initiates the discussion with a COMPUTE request, then the server responds with an ERROR or a RESULT depending of the validity of the previous request. The client might initiatie as many computations as he wants. When done, the connection is closed by a GOODBYE_MY_LOVER request and the server ends the discussion with a GOODBYE_MY_FRIEND.

### <u>What happens when a message is received from the other party? (semantics)</u>
    ADD op1 op2 : responds with the result of op1 + op2
    SUB op1 op2 : responds with the result of op1 - op2
    MUL op1 op2 : responds with the result of op1 * op2
    DIV op1 op2 : responds with the result of op1 / op2
    MOD op1 op2 : responds with the result of op1 % op2
    POW op1 op2 : responds with the result of op1 ^ op2
    GOODBYE_MY_LOVER : reponsds with a GOODBYE_MY_FRIEND and closes the connection

## **Specific elements (if useful)**
### <u>Supported operations</u>
 - Add
 - Susbstract
 - Multiply
 - Divide
 - Modulo
 - Power

### <u>Error handling</u>
    #ERROR - wrong syntax
    #ERROR - operation not recognized
    #ERROR - invalid numer of operands

### <u>Extensibility</u>
Add more operations later on.

## **Examples: examples of some typical dialogs.**
    client : ADD 12 8
    server : RESULT 20
    client : DIVIDE 10 2
    server : ERROR operation not recognized.
    client : GOODBYE_MY_LOVER
    server : GOODBYE_MY_FRIEND      
