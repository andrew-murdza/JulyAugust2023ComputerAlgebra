package src.actions.structure;

import actions.structure.Case;
import lombok.AllArgsConstructor;
import util.List;

@AllArgsConstructor
public class InnerCase {
    public List<Case> cases;
    public int correctCase;
    public String message;
    public InnerCase(List<Case> cases,int correctCase){
        this(cases,correctCase,"");
    }
}
