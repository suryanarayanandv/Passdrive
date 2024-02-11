package com.github.passdrive.utils;

public class PassGen {
    int PassLen;
    String passString;
    String genPass;
    boolean alph;
    boolean spclCh;
    boolean num;
    PassGen(int PassLen,boolean alph, boolean spclCh, boolean num){
        this.PassLen = PassLen;
        this.num = num;
        this.alph = alph;
        this.spclCh = spclCh;
    }

    private String Generator(String passString){
        StringBuilder sb = new StringBuilder(PassLen);
        for (int i = 0; i<PassLen; i++) {
            int index = (int)(passString.length()*Math.random());
            sb.append(passString.charAt(index));
        }
        return sb.toString();
    }

    String generatePassword(){
        if(alph & spclCh & num){
            passString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "~`!@#$%^&*()_+-=[]|}{;':/?.>,<;";
        }
        else if(alph & spclCh){
            passString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "~`!@#$%^&*()_+-=[]|}{;':/?.>,<;";
        }
        else if(alph & num){
            passString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "1234567890";
        }
        else if(num & spclCh){
            passString = "1234567890" + "~`!@#$%^&*()_+-=[]|}{;':/?.>,<;";

        }
        else if(num){
            passString = "1234567890";
        }
        else if(spclCh){
            passString = "~`!@#$%^&*()_+-=[]|}{;':/?.>,<;";
        }
        else{
            passString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz";
        }

        genPass = Generator(passString);

        return genPass;
    }
}
