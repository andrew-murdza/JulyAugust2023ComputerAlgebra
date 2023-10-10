package src.exs;

import actions.structure.Step;
import exs.ColorMap;
import lombok.AllArgsConstructor;
import util.List;

@AllArgsConstructor
public class Example {
    public String prompt;
    public Question question;
    public List<Step> steps;
    public exs.ColorMap map;
    public Example(String prompt, Question question, List<Step> steps){
        this(prompt,question,steps,new ColorMap());
    }
    public Example(String prompt, Question question, Step...steps){
        this(prompt,question, List.of(steps));
    }
    @AllArgsConstructor
    public static class Question {
        public String specific;
        public String general="";
        public Question(String specific){
            this.specific=specific;
        }
    }
}
