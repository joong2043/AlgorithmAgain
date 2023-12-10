class Solution {
    public int solution(String name) {
        int answer = 0;
        
        // 조이스틱으로 알파벳 이름 완성하기
        // 맨 처음엔 A로만 이루어져 있다.
        // 완성해야 하는 이름이 세 글자면 AAA, 네 글자면 AAAA
        // 조이스틱을 각 방향으로 움직이면 아래와 같다.
        
        // 첫 번째 위치에서 조이스틱을 위로 9번 조작 ^*9 -> J
        // 조이스틱을 왼쪽으로 1번 조작하여 커서를 마지막 문자 위치로 이동시키면 
        // ㅁㅁㅁ
        // <- 
        // 마지막 위치에서 조이스틱을 아래로 1번 조작
        // Z
        
        // 만들고자 하는 이름 name이 매개변수로 주어질 때 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수 만들기
        
        // 문자 배열을 만들 필요가 있다.
        
        char[] arr = new char[name.length()];
        for(int i = 0; i < name.length(); i++) {
            arr[i] = name.charAt(i);
        }
        
        // ABCDEFGHIJKLM(12) N(13) (12)O (11)PQRSTUVWX(2)Y(1)Z
        // "AAAAAA"
        // A -> J 위로 9번
        // A -> E 위로 4번
        // A -> R 아래로 9번
        // A -> O 아래로 12번 위로 14번
        // A -> E 위로 4번
        // A -> N 위로 13번
        
        // 좌 우
        // 위 아래 
        
        // System.out.println('J' - 'A'); // 9
        // System.out.println('A' - 'Z'); // -25
        int upDownMove;
        int leftRightMove = name.length() - 1; // 기본 이동 횟수
        
        // 좌 우로 이동하는 경우
        // 1. 처음부터 쭉 오른쪽으로 가는 경우
        // 2. 처음부터 쭉 왼쪽으로 가는 경우 ex. BAACC
        // 3. 오른쪽으로 갔다가 다시 리턴하여 왼쪽으로 가는 경우 ex.BBAAAAC
        // 4. 왼쪽으로 갔다가 다시 리턴하여 오른쪽으로 가는 경우 ex. BAAACCAAAAAAAAC
        
        // ㅁAAAㅁㅁ -> i = 0일 때, next = 4
        // J
        for(int i = 0; i < name.length(); i++) {
            // 상하이동의 최소값 찾기
            upDownMove =  Math.min(arr[i] - 'A', 'Z' - arr[i] + 1);
            
            answer += upDownMove;
            
            // 연속된 'A'가 끝나는 지점 찾기
            int nextIdx = i + 1; // next는 현재 위치에서 다음 위치
            // 연속된 A가 있을 때 까지 탐색
            while(nextIdx < name.length() && name.charAt(nextIdx)=='A') {
                nextIdx++;
            }
            
            // 좌우이동 최소값 찾기
            // 오른쪽으로 중간까지 왔다가 다시 왼쪽으로 가는 경우
            leftRightMove = Math.min(leftRightMove, (i * 2) + name.length() - nextIdx);
            // 왼쪽으로 이동을 하여 중간까지 갔다가 다시 오른쪽으로 가는 경우
            leftRightMove = Math.min(leftRightMove, (name.length() - nextIdx) * 2 + i);
            
        }

        answer += leftRightMove;

        
        return answer;
    }
}
