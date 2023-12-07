import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        // | 숫자 -> 큐에 주어진 숫자를 삽입한다
        // | 2 -> queue.add(2)

        // D1 -> 큐에서 최댓값을 삭제한다.
        // D1 => { 1, 2 } => {1}

        // D-1 => 큐에서 최솟값을 삭제한다.
        // D-1 => { 1, 2 } => { 2 }

        // 모든 연산 처리 후 큐가 비어있으면 [0,0] , 비어있지 않으면 [최댓값, 최솟값] return

        // 0. 최소Queue 생성, 최대Queue 생성
        PriorityQueue<Integer> minPq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                return n1 - n2;
            }
        });

        PriorityQueue<Integer> maxPq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                return n2 - n1;
            }
        });

        // 1. 문자열 처리
        for(int i = 0; i < operations.length; i++) {
            String[] arr = operations[i].split(" ");
            if(arr[0].equals("I")) {
                // 큐에 주어진 숫자 삽입
                int num = Integer.parseInt(arr[1]);
                minPq.add(num);
                maxPq.add(num);
            }
            else {
                // 빈 큐에 데이터 삭제 -> 연산 무시
                if(minPq.isEmpty()) {
                    continue;
                }
                // 최소큐에서 첫번째값 삭제
                // 최대큐에서 최솟값에 해당하는 값 삭제
                if(arr[1].equals("-1")) {
                    // System.out.println(minPq);
                    int minNum = minPq.poll();
                    maxPq.remove(minNum);
                }
                else { // 최대큐에서 첫번재값 삭제 // 최소큐에서 최대값에 해당하는 값 삭제
                    int maxNum = maxPq.poll();
                    minPq.remove(maxNum);
                }
            }
        }

        if(minPq.isEmpty()){
            answer[0] = 0;
            answer[1] = 0;
        }
        else {
            answer[0] = maxPq.poll();
            answer[1] = minPq.poll();
        }

        return answer;
    }
}