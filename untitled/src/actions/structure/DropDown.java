package actions.structure;

import lombok.AllArgsConstructor;
import util.List;

@AllArgsConstructor
public class DropDown {
    String title;
    int caseI;
    List<String> options;
    public DropDown(String title, int caseI, String... options){
        this(title,caseI,List.of(options));
    }
}
