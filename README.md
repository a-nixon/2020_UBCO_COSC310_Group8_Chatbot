# 2020_UBCO_COSC310_Group8_Chatbot
Chatbot project for COSC 310 group 8 at UBCO
Made by Alec Nixon with contribution from Coleton Weninger  


## DataBase Features:  
* Multiple responses can be stored per query  
* Header with metadata  
* Uses standard data-structures



## Chatbot Features:  
* **Database files**  
Stores all chat dialogue in a database file. Easily change dialogue by specifying a different database.
* **Database Manager** (AKA Respose Manager)  
A GUI interface for editing chatbot respose databases.
* **Networking**  
Chatbot can act as a server, client or remote terminal for interacting with other chatbots. 
Terminal mode does not generate any conversation, it allows users to connect to and interact
with a remote chatbot (or any sockts server).  
* **Unknown Input Handling**  
Can provide responses for unknown input, and can provide different responses if part of the input is understandable.
* **Context Memory**
Will remember the last input such that missing context in a new input can be filled in.
