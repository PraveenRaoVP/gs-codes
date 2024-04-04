package evals.eval1;

/*Given an array health[] where health[i] is the health of the ith player in a game, any
player can attack any other player in the game. The health of the player being attacked
will be reduced by the amount of health the attacking player has. The task is to find the
minimum possible health of the winning player. (PS- Always the player with lower health
will attack the player with higher health)
Sample 1 - Input: health[] = {4, 6, 8}
Output: 2
4 attacks 6, health[] = {4, 2, 8}
2 attacks 4 twice, health[] = {0, 2, 8}
2 attacks 8 four times, health[] = {0, 2, 0}
Sample 2 - Input: health[] = {4, 1, 5, 3}
Output: 1 */

public class GameHealth {
    public static void main(String[] args) {
        int[] health = {4,6,8};
        System.out.println(minHealth(health));
    }

    public static int minHealth(int[] health) {
        int min = Integer.MAX_VALUE;
        for(int i=0;i<health.length;i++) {
            if(health[i] < min) {
                min = health[i];
            }
        }
        return min;
    }
}
