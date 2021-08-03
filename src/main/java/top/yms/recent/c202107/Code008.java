package top.yms.recent.c202107;

public class Code008 {

    public boolean exist(char[][] board, String word) {
        if (board == null || word == null) return false;
        int rowLen = board.length;
        if (rowLen == 0) return false;
        int colLen = board[0].length;
        if (colLen == 0) return false;

        char [] wordChars = word.toCharArray();
        for(int i=0; i<rowLen; i++) {
            for(int j=0; j<colLen; j++) {
                if (doDFS(board, i, j, wordChars, 0)) return true;
            }
        }
        return false;
    }

    public boolean doDFS(char [] [] board, int i, int j, char [] word, int k) {
        if ((i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j] != word[k])) return false;
        if (k == word.length -1 ) return true;
        board[i][j] = '\0';
        boolean res = doDFS(board,i, j+1, word, k+1) ||
                      doDFS(board, i, j-1, word, k+1) ||
                        doDFS(board, i+1, j, word, k+1) ||
                        doDFS(board, i-1, j, word, k+1);
        board[i][j] = word[k];
        return res;
    }

    public static void main(String[] args) {
        char [] [] testDemo1 = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };

        String testStr1 = "ABCCED";

        Code008 code008 = new Code008();

        char [] [] testDemo2 = new char[][] {};
        char [] [] testDemo3 = new char[][] {{}};

        System.out.println(code008.exist(testDemo1, testStr1));
        System.out.println(code008.exist(testDemo2, testStr1));
        System.out.println(code008.exist(testDemo3, testStr1));



    }

}
