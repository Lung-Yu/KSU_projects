package com.dev.lungyu.homecontroller.tools.interpreter;

/**
 * Created by lungyu on 3/5/18.
 */

public class OptionInterpreter {

    public OptionInterpreter(int optionId){
        this.optionId = optionId;
    }

    public OptionInterpreter(){

    }

    private int optionId;

    public void setOption(String opt){
        if(opt.equals(">"))
            this.optionId = 0;
        else if(opt.equals("<"))
            this.optionId = 1;
        else if (opt.equals("="))
            this.optionId = 2;
        else if(opt.equals(">="))
            this.optionId = 3;
        else if (opt.equals("<="))
            this.optionId = 4;
    }

    public String getOption(){
        switch (optionId){
            case 0: return ">";
            case 1: return "<";
            case 2: return "=";
            case 3: return ">=";
            case 4: return "<=";
            default: return "?";
        }
    }

    public void setOptionId(int optionId){
        this.optionId = optionId;
    }

    public int getOptionId(){
        return this.optionId;
    }
}
