package techsupport4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * The responder class represents a response generator object.
 * It is used to generate an automatic response, based on specified input.
 * Input is presented to the responder as a set of words, and based on those
 * words the responder will generate a String that represents the response.
 *
 * Internally, the reponder uses a HashMap to associate words with response
 * strings and a list of default responses. If any of the input words is found
 * in the HashMap, the corresponding response is returned. If none of the input
 * words is recognized, one of the default responses is randomly chosen.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @version    1.0 (2016.02.29)
 */
public class Responder
{
    // Used to map key words to responses.
    
    private HashMap<String, String> responseMap;
    // Default responses to use if we don't recognise a word.
    private ArrayList<String> defaultResponses;
    private HashMap<String, Integer> inputWords;
    private Random randomGenerator;
    private int responseCount = 0;

    /**
     * Construct a Responder
     */
    public Responder()
    {
        responseMap = new HashMap<>();
        defaultResponses = new ArrayList<>();
        inputWords = new HashMap<>();
        fillResponseMap();
        fillDefaultResponses();
        randomGenerator = new Random();
    }

    
    public String generateResponse(HashSet<String> words)
    {
        for (String word : words) { // looping through the user input 'words'
            if(inputWords.get(word)==null){
                inputWords.put(word,1); // if a new word is detected
            } else {
                inputWords.put(word,inputWords.get(word) +1); // adding the input to the map of words and their respective frequencies
            }
            String response = responseMap.get(word); // creating a 'response' by picking from the available responses            
            if(response != null) { // if the response picked is not null
                if(responseCount % 5!=0){ // if the number of responses provided is not divisible by 5
                    responseCount += 1; // increment number of responses
                    if(!inputWords.containsKey(word)){
                    response = pickDefaultResponse(); // get a new response from the default responses
                    }
                }
                else if(responseCount % 5==0){ // for every 5 input-response events
                    responseCount+=1; // increment count
                }
            //String[] responseArray = response.split(".");
            //randomGenerator.nextInt(responseArray.size());
            return response; // return the response
            }     
        }
        
        // If we get here, none of the words from the input line was recognized.
        // In this case we pick one of our default responses (what we say when
        // we cannot think of anything else to say...)
        return pickDefaultResponse();
    }

       /**
     * Enter all the known keywords and their associated responses
     * into our response map.
     */
    private void fillResponseMap()
    {
        responseMap.put("crash", 
                        "Well, it never crashes on our system.\n" +
                        "It must have something to do with your system.\n" +
                        "Tell me more about your configuration.");
        responseMap.put("crashes", 
                        "That is really unfortunate.\n" +
                        "I recommend that you check for a virus.\n" +
                        "Is there software running that you do not recognize?");
        responseMap.put("slow", 
                        "I think this has to do with your hardware.\n" +
                        "Upgrading your processor should solve all performance problems.\n" +
                        "Have you got a problem with our software?");
        responseMap.put("performance", 
                        "Performance was quite adequate in all our tests.\n" +
                        "Are you running any other processes in the background?");
        responseMap.put("bug", 
                        "Well, you know, all software has some bugs.\n" +
                        "Our software engineers are working very hard to fix them.\n" +
                        "Can you describe the problem a bit further?");
        responseMap.put("buggy", 
                        "It is possible there are some bugs.\n" +
                        "We can only work on problems that are brought to our attention.\n" +
                        "It might help if you described the problem a bit more.");
        responseMap.put("windows", 
                        "This is a known bug to do with the Windows operating system.\n" +
                        "Please report it to Microsoft.\n" +
                        "There is nothing we can do about this.");
        responseMap.put("mac", 
                        "This is a known bug to do with the Mac operating system.\n" +
                        "Please report it to Apple.\n" +
                        "rThere is nothing we can do about this.");
        responseMap.put("expensive", 
                        "The cost of our product is quite competitive.\n" +
                        "Have you looked around and really compared our features?");
        responseMap.put("installation", 
                        "The installation is really quite straight forward.\n" +
                        "We have tons of wizards that do all the work for you.\n" +
                        "Have you read the installation instructions?");
        responseMap.put("memory", 
                        "If you read the system requirements carefully.\n" +
                        "The specified memory requirements are 1.5 giga byte.\n" +
                        "You really should upgrade your memory.\n");
        responseMap.put("linux", 
                        "We take Linux support very seriously.\n" +
                        "There are some problems.\n" +
                        "Most of the problems have to do with incompatible glibc versions.\n" +
                        "Can you be a bit more precise?");
        responseMap.put("bluej", 
                        "BlueJ, yes.\n" +
                        "We tried to buy out those guys long ago.\n" +
                        "They simply won't sell... Stubborn people they are.\n" +
                        "We don't support it, I'm afraid.");
    }
    

    /**
     * Build up a list of default responses from which we can pick one
     * if we don't know what else to say.
     */
    private void fillDefaultResponses()
    {
        defaultResponses.add("That sounds odd. Could you describe that problem in more detail?");
        defaultResponses.add("No other customer has ever complained about this before. \n" +
                             "What is your system configuration?");
        defaultResponses.add("That sounds interesting. Tell me more...");
        defaultResponses.add("I need a bit more information on that.");
        defaultResponses.add("Have you checked that you do not have a dll conflict?");
        defaultResponses.add("That is explained in the manual. Have you read the manual?");
        defaultResponses.add("Your description is a bit wishy-washy. Have you got an expert\n" +
                             "there with you who could describe this more precisely?");
        defaultResponses.add("That's not a bug, it's a feature!");
        defaultResponses.add("Could you elaborate on that?");
    }

    /**
     * Randomly select and return one of the default responses.
     * @return     A random default response
     */
    private String pickDefaultResponse()
    {
        // Pick a random number for the index in the default response list.
        // The number will be between 0 (inclusive) and the size of the list (exclusive).
        int index = randomGenerator.nextInt(defaultResponses.size());
        return defaultResponses.get(index);
    }
}