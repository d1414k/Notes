// https://practice.geeksforgeeks.org/problems/largest-number-in-k-swaps-1587115620/1
class Solution{
    static String res = "";
    public static String findMaximumNum(String str, int k){
        res = str;
        helper(str.toCharArray(),0,k);
        return res;
    }
    
    static void helper(char []s,int index, int k){
        
        if(String.valueOf(s).compareTo(res) > 0){
            res = String.valueOf(s);
        } //System.out.println(res +" "+String.valueOf(s)+" "+index+" "+k);
        if(index >= s.length-1 || k == 0) return;
        char max = s[index];
        for(int i = index + 1; i < s.length ; i++){
            if(s[i] > max)
                max = s[i];
        }
        
        for(int i = index + 1 ; i < s.length ; i++){
            if(s[i] == max){
                s[i] = s[index];
                s[index] = max;
                helper(s,index+1,k-1);
                s[index] = s[i];
                s[i] = max;
            }
        }
        if(s[index] == max) helper(s,index+1,k);
    }
}
