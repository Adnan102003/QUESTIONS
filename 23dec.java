In the village of Arcadia, there was a renowned gardener named Elysia, famous for her intricate garden maze (NxM). Maze, the "Path of Harmony," had four main paths: the Path of the Sun (Forward), Path of Shadows (Backward), Path of Winds (Left), and Path of Dawn (Right). Adventurers from around the world would visit to test their navigation skills.

However, Elysia noticed that adventurers often used unnecessary movements—backtracking or alternating left and right repeatedly—which slowed them down. Elysia wanted to help them make their journey smoother by removing redundant instructions while ensuring they still reached the centre of the maze.

One day, a group of adventurers starts at (0,0) of the maze with a string of instructions describing their movements. Elysia challenged them to find the maximum number of steps they could delete while still reaching the center of the maze.

The adventurers analyzed their steps carefully, removing every unnecessary move, and learned the art of navigating the maze efficiently with minimal instructions, all while reaching the same final destination. The Path of Harmony became even more popular as adventurers mastered this skill.

Input Format
The first line of input contains two space separted integers N and M, representing the dimension of the maze.

The second line of input contains a string S, represeting the instruction of the movement.

Output Format
Print the maximum number of the instructions they can delete.

If it is impossible to reach the middle of the maze, print -1.

Constraints
N, M ∈ {1, 3, 5,…,999}

We can move freely in the maze.

String contains character {F, B, L, R}

1 <= |String| <= 1000

Sample Testcase 0
Testcase Input
3 3
RRFLF
Testcase Output
3
Explanation
Consider a matrix where free movement is allowed, and each cell is represented by 1:
          1 1 1
          1 1 1
          1 1 1


You start at the top-left corner (0,0) and need to reach the middle cell of the matrix. The movement instructions provided are: RRFLF.


To reach the middle cell, you can move 1 step to the right and 1 step forward (or vice versa), requiring only 2 steps. The remaining instructions are unnecessary.


Thus, the maximum number of redundant steps that can be removed is 3.

Sample Testcase 1
Testcase Input
5 5
FFLLRRBBFRR
Testcase Output
7
Explanation
The number of extra movements for this testcase is 7.

SOLUTION:::
import java.util.*;

public class Main {
    public static int maximizeDeletedSteps(int N, int M, String S) {
        int targetX = M / 2;
        int targetY = N / 2;
        
        int countR = 0, countF = 0;
        
        for (char c : S.toCharArray()) {
            if (c == 'R') countR++;
            if (c == 'F') countF++;
        }
        
        if (countR < targetX || countF < targetY) {
            return -1;
        }
        
        int minSteps = targetX + targetY;
        
        return S.length() - minSteps;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        String S = scanner.next();
        
        int result = maximizeDeletedSteps(N, M, S);
        System.out.println(result);
    }
}
