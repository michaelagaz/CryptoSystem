package com.company;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

/**
 * Created by Michaela on 29.10.2017.
 */
public class Automaton {
    private Grammar grammar;
    String word = "0011001";
    String tobeParsed = "abba";

    public Automaton(Grammar grammar) {
        this.grammar = grammar;
    }

    public Automaton() {
        Grammar grammar = new Grammar(addNonTerminals(addRules()),addTerminals(addRules()),"S",addRules());
        this.grammar = grammar;
        System.out.println("NonTerminals: "+addNonTerminals(addRules()));
        System.out.println("Terminals: "+addTerminals(addRules()));
        grammar.getRulesOutput(addRules());

        encryptWord(encodeBinaryWord(word));
        List<Rule> usedRules =cykParsing(tobeParsed, addRules());
        System.out.println();
    }

    public Grammar getGrammar() {
        return grammar;
    }

    public void setGrammar(Grammar grammar) {
        this.grammar = grammar;
    }


    //Rules for Grammar in Lecture Sheet ---> hardcoded
    public List<Rule> addRules() {
        List<Rule> rules = new ArrayList<>();

        rules.add(new Rule("r1", "S", "AB"));
        rules.add(new Rule("r2", "S", "cAB"));
        rules.add(new Rule("r3", "A", "aAb"));
        rules.add(new Rule("r4", "A", "b"));
        rules.add(new Rule("r5", "B", "AB"));
        rules.add(new Rule("r6" , "B", "c"));


        return rules;
    }

    public List<String> addNonTerminals(List<Rule> rules) {
        List<String> nonTerminals = new ArrayList<>();
        for (Rule rule : rules) {
            for(int i = 0; i < rule.getLeftSide().length(); i++){
                if(isUpperCase(rule.getLeftSide().charAt(i))){
                    if(!nonTerminals.contains(String.valueOf(rule.getLeftSide().charAt(i)))){
                        nonTerminals.add(String.valueOf(rule.getLeftSide().charAt(i)));
                    }
                }
            }
        }
        return nonTerminals;
    }
    public List<String> addTerminals(List<Rule> rules) {
        List<String> terminals = new ArrayList<>();
        for (Rule rule : rules) {
            for(int i = 0; i < rule.getRightSide().length(); i++){
                if(isLowerCase(rule.getRightSide().charAt(i))){
                    if(!terminals.contains(String.valueOf(rule.getRightSide().charAt(i)))){
                        terminals.add(String.valueOf(rule.getRightSide().charAt(i)));
                    }
                }
            }
        }
        return terminals;
    }

    public String encodeBinaryWord(String word){
        String encodedWord = "";
        for(int i=0; i <word.length(); i++){
            encodedWord += word.charAt(i) == '0' ? "r3,": "r5,";
        }
        System.out.println(encodedWord);
        return encodedWord;
    }

    public String encryptWord(String encodedWord){
    String encryptedWord = "";
    String[] splitArray = parseEncodedWord(encodedWord);

    List<Rule> encodedRules = toRules(splitArray);

    encryptedWord = getGrammar().getRules().get(1).getRightSide();
    for(int i = 0 ; i<encodedRules.size(); i++){
        for(int j = 0 ; j< encryptedWord.length(); j++){
            System.out.println(encodedRules.get(i).getLeftSide().charAt(0));
            if(encryptedWord.charAt(j) == encodedRules.get(i).getLeftSide().charAt(0)){
            //TODO: replace encrypted[j] za pravu
                encryptedWord.replaceFirst(String.valueOf(encryptedWord.charAt(j)), encodedRules.get(i).getRightSide());
            }
        }
    }
        System.out.println("encrypted word "+encryptedWord);

        return encryptedWord;
    }

    public String[] parseEncodedWord(String encodedWord){
        String[] splitArray = encodedWord.split(",");
        System.out.println("splitArray "+ splitArray[0]);
        return splitArray;
    }

    public List<Rule> toRules(String[] array){
        List<Rule> rules = new ArrayList<>();

        for(int i= 0; i < array.length; i++){
           rules.add(getGrammar().findRuleByLabel(array[i]));
        }
        return rules;
    }


    public List<Rule> cykParsing(String word, List<Rule> rules){
        List<String> usedRules = new ArrayList<>();
        List<Rule> userRulerules = new ArrayList<>();
        String rulesInWord = "";
        for(int i=0; i< word.length(); i++){
            for(int j=0; j< rules.size(); j++) {
                if (rules.get(j).getRightSide().contains(String.valueOf(word.charAt(i)))){
                    rulesInWord +=rules.get(j).getLeftSide();
                }
            }
            usedRules.add(rulesInWord);
            rulesInWord = "";
        }

        for(int i = 0 ;i< usedRules.size(); i++){
            for(int j=0; j< usedRules.get(i).length(); i++){
                for(int k=0; k<rules.size(); i++){
                    if(String.valueOf(usedRules.get(i).charAt(j))==rules.get(k).getRightSide()){
                        userRulerules.add(rules.get(k));
                    }
                }

            }

        }



        return userRulerules;
    }

}
