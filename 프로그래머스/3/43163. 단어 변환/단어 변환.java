import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        
        Set<String> set = new HashSet<>(Arrays.asList(words));
        if (!set.contains(target)) return 0;
        
        Map<String, List<String>> map = new HashMap<>();
        for (String w : words){
            map.put(w,new ArrayList<>());
        }
        map.put(begin,new ArrayList<>());
        
        for (String word : words){
            if (isOneDiff(begin, word)){
                map.get(begin).add(word);
                map.get(word).add(begin);
            }
        }
        
        for (String w1 : words){
            for (String w2 : words){
                if (isOneDiff(w1,w2)){
                    map.get(w1).add(w2);
                    map.get(w2).add(w1);
                }
            }
        }
        
        Queue<Map.Entry<String,Integer>> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        visited.add(begin);
        queue.offer(new AbstractMap.SimpleEntry(begin,0));
        
        while (!queue.isEmpty()){
            Map.Entry<String,Integer> temp = queue.poll();
            Integer time = temp.getValue();
            String curr = temp.getKey();
            
            if (curr.equals(target)) return time;
            
            for (String next : map.get(curr)){
                if (!visited.contains(next)){
                    visited.add(next);
                    queue.offer(new AbstractMap.SimpleEntry(next,time+1));
                }
            }
        }
        
        
        return 0;
    }
    
    public static boolean isOneDiff(String w1, String w2){
        String[] temp1 = w1.split("");
        String[] temp2 = w2.split("");
        
        if (w1.equals(w2)) return false;
        
        int count = 0;
        for (int i = 0 ; i < temp1.length ; i++){
            if (temp1[i].equals(temp2[i])) count++;
        }
        if (count == w1.length() - 1) return true;
        
        return false;
    }
    
}