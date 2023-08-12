package genBool;

public enum GenBool {
    TRUE,FALSE,UNKNOWN;
    public boolean isTrue(){
        return this == GenBool.TRUE;
    }
    public GenBool and(GenBool bool){
        if(this==TRUE&&bool==TRUE){
            return TRUE;
        }
        if(this==FALSE||bool==FALSE){
            return FALSE;
        }
        return UNKNOWN;
    }
    public GenBool or(GenBool bool){
        if(this==TRUE||bool==TRUE){
            return TRUE;
        }
        if(this==FALSE&&bool==FALSE){
            return FALSE;
        }
        return UNKNOWN;
    }
    public boolean isFalse(){
        return this == GenBool.FALSE;
    }
}
