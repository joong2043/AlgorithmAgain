import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = people.length;
        // 사람들의 몸무게가 70, 50, 80, 50
        // 구명보트의 무게 제한이 100
        // 최대 2명 
        // 구명보트를 최대한 적게 사용

        // 20 30 40 60 70 80
        Arrays.sort(people);
        int j = 0;
        for(int i = people.length -1; i>=0; i--){

            if(j<i && people[i] + people[j] <= limit){
                // System.out.println(j+" "+i);
                answer--;
                j = j + 1;
            }

        }

        return answer;
    }
}