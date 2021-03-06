package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michaela on 19.10.2017.
 */
public class Grammar {
    private List<String> nonTerminals;
    private List<String> terminals;
    private String initTerminal;
    private List<Rule> rules;

    public Grammar(List<String> nonTerminals, List<String> terminals, String initTerminal, List<Rule> rules) {
        this.nonTerminals = nonTerminals;
        this.terminals = terminals;
        this.initTerminal = initTerminal;
        this.rules = rules;
    }

    public Grammar() {
    }


    public List<String> getNonTerminals() {
        return nonTerminals;
    }

    public void setNonTerminals(List<String> nonTerminals) {
        this.nonTerminals = nonTerminals;
    }

    public List<String> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<String> terminals) {
        this.terminals = terminals;
    }

    public String getInitTerminal() {
        return initTerminal;
    }

    public void setInitTerminal(String initTerminal) {
        this.initTerminal = initTerminal;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }


    public Rule findRuleByLabel(String label){
        for(int i = 0; i<getRules().size(); i++){
            if(getRules().get(i).getLabel().equals(label)){
                return getRules().get(i);
            }
        }
        return null;
    }

    public String generateWord() {

        return "";
    }

    public void getRulesOutput(List<Rule> rules) {
        List<String> output = new ArrayList<>();
        for (Rule rule : rules) {
            output.add(rule.getLabel() + ":" + rule.getLeftSide() + "-->" + rule.getRightSide());
        }

        for(String s: output){
            System.out.println(s);
        }
    }
}
