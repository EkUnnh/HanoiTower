import java.util.ArrayList;
import java.util.List;

public class HanoiTower {

    private List<List<Integer>> pillars; // 三根柱子上盘子的状态，第一根柱子为0，第二根柱子为1，第三根柱子为2
    private List<int[]> moves; // 记录盘子移动的历史，每个元素是一个长度为2的数组，表示移动的起点和终点

    public HanoiTower(int n) {
        pillars = new ArrayList<>();
        pillars.add(new ArrayList<>());
        pillars.add(new ArrayList<>());
        pillars.add(new ArrayList<>());
        for (int i = n; i >= 1; i--) {
            pillars.get(0).add(i);
        }
        moves = new ArrayList<>();
    }

    public boolean move(int from, int to) {
        if (from < 0 || from > 2 || to < 0 || to > 2) {
            return false;
        }
        if (pillars.get(from).isEmpty()) {
            return false;
        }
        if (!pillars.get(to).isEmpty() && pillars.get(from).get(pillars.get(from).size() - 1) > pillars.get(to).get(pillars.get(to).size() - 1)) {
            return false;
        }
        int disk = pillars.get(from).remove(pillars.get(from).size() - 1);
        pillars.get(to).add(disk);
        moves.add(new int[] {from, to});
        return true;
    }

    public boolean isFinished() {
        return pillars.get(0).isEmpty() && pillars.get(1).isEmpty();
    }

    public int[] getLastMove() {
        if (moves.isEmpty()) {
            return null;
        }
        return moves.get(moves.size() - 1);
    }

}
