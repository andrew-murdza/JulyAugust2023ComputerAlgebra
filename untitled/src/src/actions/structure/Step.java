package src.actions.structure;

import actions.structure.DropDown;
import actions.structure.InnerCase;
import util.List;

public class Step {
    public String title;
    public List<Step> substeps;
    public String str;
    public String strAfter;
    public List<InnerCase> innerCases;

    public List<DropDown> dropdowns;

    public Step(String title){
        this.title=title;
    }
    public Step(String title, String str){
        this.title=title;
        this.str=str;
    }
    public Step(String title, List<Step> steps){
        this.title=title;
        this.substeps=steps;
    }
    public Step(String title, String str, List<Step> steps){
        this.title=title;
        this.str=str;
        this.substeps=steps;
    }
    public Step(String title, Step... steps){
        this(title,List.of(steps));
    }
}
