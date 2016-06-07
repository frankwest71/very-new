------------------------------------------------------------------------
Requirements - Must be programmed in Java or Scala
 
Write a command-line program that prompts the user for a topic at the terminal, and then prints out the introductory paragraph of the Wikipedia page for that topic, without HTML tags. The program should ask the user to enter the query at a prompt. If the user does not enter a topic (enters a blank one), they should be prompted again. If there is no page for that topic the program should print "Not found." and terminate. The program should also terminate after printing a successful result.
All you should need to know for this task is that English-language articles for Wikipedia are found at the following URL: http://en.wikipedia.org/wiki/<Topic>. When there are spaces in a topic, the spaces should be replaced with underscores. You will receive bonus points if the topic can be provided as a command-line argument as well as at a prompt if no argument is provided.
You should document all of your methods appropriately with javadoc (or scaladoc), explaining the purpose of each parameter and possible outcomes. Also, please include a short explanation of your approach to the problem, and why you chose any particular libraries used. You may use anything from the Java or Scala standard libraries, as well as any third party libraries.
When finished, you should commit your source code to a git repository, then push that repository to a github.com account, and provide us with the link to view it.  If you do use any third party libraries, please include the necessary dependencies (JAR files) in the repository as well, for convenience.
------------------------------------------------------------------------

PROJECT TITLE: Wikipedia Search
PURPOSE OF PROJECT: Job Assessment
VERSION or DATE: 6/7/2016
HOW TO START THIS PROJECT: Download BlueJ. This code is designed to run with BlueJ
AUTHORS: Hao
USER INSTRUCTIONS: Open up BlueJ. Make a new project. Make a new class. Copy/paste code into a class. Compile and run. This project needs the two jar files to run including Jsoup and HtmlUnit. Go to Preferences, Libaries, and add the jar files to the library, then reload BlueJ.
