package LeetCode;

public class _419_Battleships {
	 public int countBattleships(char[][] board) {
	        if (board == null || board.length < 1 || board[0] == null || board[0].length < 1) {
	            return 0;
	        }
	        
	        int ans = 0;
	        
	        for (int i = 0;i < board.length;i++) {
	            for (int j = 0;j < board[i].length;j++) {
	                if (board[i][j] == 'X') {
	                    dfs(board,i,j);
	                    ans++;
	                }
	            }
	        }
	        
	        return ans;
	    }
	    
	    private void dfs(char[][] board,int i,int j) {
	        board[i][j] = '.';
	        
	        if (i + 1 < board.length && board[i + 1][j] == 'X') {
	            dfs(board,i + 1,j);
	        }
	        
	        if (j + 1 < board[i].length && board[i][j + 1] == 'X') {
	            dfs(board,i,j + 1);
	        }
	    }
}
