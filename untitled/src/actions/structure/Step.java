package actions.structure;

import java.util.List;

public class Step {
    public String title;
    public List<Step> substeps;
    public String str;
    public String strAfter;
    public List<InnerCase> innerCases;

    public Step(String title){
        this.title=title;
    }
    public Step(String title, String str){
        this.title=title;
        this.str=str;
    }
}
