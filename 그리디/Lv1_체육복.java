import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        // 점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했다.

        // 학생들의 번호는 체격 순으로 매겨져있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복 빌려주기 가능

        // 전체 학생 수 : n
        // 체육복을 도난당한 학생들의 번호가 담긴 배열 : lost
        // 여별의 체육복을 가져온 학생들의 번호가 담긴 배열 reverse
        // 체육수업을 들을 수 있는 학생의 최댓값 return

        // ex. 1번 학생이 2번 학생에게 체육복을 빌려주고, 3번 학생이나 5번 학생이 4번 학생에게 체육복을 빌려주면 학생 5명이 체육수업을 들을 수 있다.

        // 단계
        // 1. reserve -> lost에 앞 뒤로 체육복을 줄 수 있다
        // 정렬을 하자

        Arrays.sort(lost);
        Arrays.sort(reserve);

        // 번호가 같은 경우 (여벌 체육복을 가지고 있는 사람이 도난당한 경우) {
        for(int i = 0; i < lost.length; i++) {
            for(int j = 0; j < reserve.length; j++) {
                if(lost[i] == reserve[j]) {
                    lost[i] = -1;
                    reserve[j] = -1;
                    break;
                }
            }
        }

        // reserve 1 3 5
        // lost 2 4
        // 번호가 하나씩 차이가 나는 경우
        for(int i = 0; i < lost.length; i++) {
            for(int j = 0; j < reserve.length; j++) {
                if(Math.abs(lost[i] - reserve[j]) == 1) {
                    lost[i] = -1;
                    reserve[j] = -1;
                    break;
                }
            }
        }

        int lostNum = 0;
        for(int i = 0; i < lost.length; i++) {
            if(lost[i] != -1) {
                lostNum++;
            }
        }

        answer = n - lostNum;
        return answer;
    }
}