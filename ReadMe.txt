Download Source.zip and unzip the files.
Open a command prompt and change the directory to xampp, then type mysql_start
Open another command prompt and change the directory to xampp/mysql/bin
Type mysql -h localhost -u student -p
password = hello
Type use student_space
Type source c:\path\cheatcardgame.sql
After getting the table in mysql, open up two more command prompts
In both command prompts, change your directory to that of the unzipped Source file (specifically c:\path\repository\CheatCardGame)
In one command prompt, type cheatcardgameServer.bat and hit enter
When the server starts, be sure to hit the listen button
In the other command prompt, type cheatcardgameClient.bat and hit enter
To add multiple clients, repeat the previous steps for the client.