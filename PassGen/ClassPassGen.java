package org.penpas.PassGen;

public class ClassPassGen {
    int PassLen;
    boolean alph = true;
    boolean spclCh = false;
    boolean num = true;
    String passString;
    String genPass;
    ClassPassGen(int PassLen){
        this.PassLen = PassLen;
    }

    private String Generator(String passString){
        StringBuilder sb = new StringBuilder(PassLen);
        for (int i = 0; i<PassLen; i++) {
            int index = (int)(passString.length()*Math.random());
            sb.append(passString.charAt(index));
        }
        return sb.toString();
    }

    String PassGen(){
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
