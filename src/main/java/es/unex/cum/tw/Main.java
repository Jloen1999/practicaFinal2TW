package es.unex.cum.tw;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> env = System.getenv();

        for (int i = 0; i < env.size(); i++) {
            String key = (String) env.keySet().toArray()[i];
            if(key.equalsIgnoreCase("LOCALAPPDATA")){
                System.out.println(env.get(key));
            }

        }
    }
}
