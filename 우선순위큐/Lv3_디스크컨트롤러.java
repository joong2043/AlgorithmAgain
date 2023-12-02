import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;

        // A작업 0ms ~ 3ms
        // B작업 1ms ~ 9ms
        // C작업 2ms ~ 6ms

        // A : 3ms 시점에 작업 완료
        // B : 1ms 부터 대기하다가, 3ms 시점에 작업을 시작해서 12ms 시점에 작업 완료
        // C : 2ms 부터 대기하다가, 12ms 시점에 작업을 시작해서 18ms 시점에 작업 완료

        // A -> 요청에서 종료까지 3ms
        // B -> 요청에서 종료까지 11ms
        // C -> 요청에서 종료까지 16ms
        // But A -> C -> B 순서로 하면 평균 9ms 소요

        // 시작시간 순서대로? nono
        // 모든 경우의 수 따져봐야

        // 필요한 점 : 시간 순으로 먼저 정렬하기 + 해당 요청으로부터 종료시간(소요시간)이 가장 짧은거 고르기

        // 시간 순으로 먼저 정렬하기
        // List의 정렬 사용하기
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        // Node를 우선순위큐에 넣기
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int processedCount = 0;
        int requestedCount = 0;
        int now = 0;
        while (processedCount < jobs.length) {
            for (int i = requestedCount; i < jobs.length; i++) {
                int requestedTime = jobs[i][0];
                int workingTime = jobs[i][1];

                // 만약 요청시간이 현재 시간보다 먼저 있는 경우
                if(requestedTime <= now) {
                    pq.add(new Node(requestedTime, workingTime));
                    requestedCount++;
                    continue;
                }

                break;
            }

            if (pq.isEmpty()) {
                now++; // pq가 비어있으면 현재시간을 1씩 늘린다
                continue;
            }

            Node node = pq.poll();

            now += node.workingTime;
            answer += now - node.requestedTime;

            processedCount++;

        }

        return answer / jobs.length;
    }
}

class Node implements Comparable<Node>{
    int requestedTime;
    int workingTime;

    Node(int requestedTime, int workingTime) {
        this.requestedTime = requestedTime;
        this.workingTime = workingTime;
    }

    @Override
    public int compareTo(Node node) {
        if(this.workingTime == node.workingTime) {
            return this.requestedTime - node.requestedTime;
        }
        return this.workingTime - node.workingTime;
    }
}