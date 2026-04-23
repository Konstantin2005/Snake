//import java.util.LinkedList;
//import java.util.List;
//
//class SnakeAI {
//    public int[] getNextMove(int[][] grid, int[] snakeHead, List<int[]> snakeBody, int[] food) {
//        Queue<Node> queue = new LinkedList<>();
//        boolean[][] visited = new boolean[grid.length][grid[0].length];
//
//        queue.add(new Node(snakeHead[0], snakeHead[1], null));
//        visited[snakeHead[0]][snakeHead[1]] = true;
//
//        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}}; // вверх, вниз, влево, вправо
//
//        while (!queue.isEmpty()) {
//            Node current = queue.poll();
//
//            if (current.x == food[0] && current.y == food[1]) {
//                return current.getFirstMove();
//            }
//
//            for (int[] dir : directions) {
//                int newX = current.x + dir[0];
//                int newY = current.y + dir[1];
//
//                if (isValid(newX, newY, grid, visited, snakeBody)) {
//                    visited[newX][newY] = true;
//                    queue.add(new Node(newX, newY, current));
//                }
//            }
//        }
//
//        return new int[]{0, 0}; // путь не найден
//    }
//
//    private boolean isValid(int x, int y, int[][] grid, boolean[][] visited, List<int[]> body) {
//        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return false;
//        if (visited[x][y]) return false;
//        if (grid[x][y] == -1) return false; // стена
//
//        // проверяем, не на теле ли змейки (кроме хвоста)
//        for (int i = 0; i < body.size() - 1; i++) {
//            if (body.get(i)[0] == x && body.get(i)[1] == y) return false;
//        }
//
//        return true;
//    }
//
//    static class Node {
//        int x, y;
//        Node parent;
//
//        Node(int x, int y, Node parent) {
//            this.x = x;
//            this.y = y;
//            this.parent = parent;
//        }
//
//        int[] getFirstMove() {
//            if (parent == null) return new int[]{x, y};
//            Node current = this;
//            while (current.parent.parent != null) {
//                current = current.parent;
//            }
//            return new int[]{current.x - current.parent.x, current.y - current.parent.y};
//        }
//    }
//}