import com.fx2048.Model.Board;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
class BoardTest {
    private Board board;

    @BeforeEach
    void setBoard() {
        this.board = new Board();
    }

    @Test
    void getSize() {
        assertEquals(4, board.getSize());
        assertNotEquals(5, board.getSize());
        assertNotEquals(1, board.getSize());
        this.board = new Board(5);
        assertNotEquals(4, board.getSize());
        assertEquals(5, board.getSize());
    }

    @Test
    void getValue() {
        assertEquals(0, board.getValue(1, 1));
        assertEquals(0, board.getValue(1, 3));
        board.setValue(2, 3, 8);
        assertEquals(8, board.getValue(2, 3));
    }

    @Test
    void setValue() {
        board.setValue(2, 3, 8);
        assertEquals(8, board.getValue(2, 3));
        board.setValue(2, 3, 10);
        assertEquals(10, board.getValue(2, 3));
        assertNotEquals(8, board.getValue(2, 3));
    }

    @Test
    void getScore() {
        assertEquals(0, board.getScore());
        board.setValue(0, 1, 8);
        board.setValue(0, 0, 8);
        board.moveLeft();
        assertEquals(16, board.getScore());
    }

    @Test
    void moveUp() {
        assertEquals(0, board.getValue(0, 0));
        board.setValue(1, 0, 2);
        assertEquals(0, board.getValue(0, 0));
        board.moveUp();
        assertEquals(2, board.getValue(0, 0)); //move check
        board.setValue(1, 0, 2);
        board.moveUp();
        assertEquals(4, board.getValue(0, 0)); //merge check
    }

    @Test
    void moveDown() {
        assertEquals(0, board.getValue(3, 0));
        board.setValue(2, 0, 2);
        assertEquals(0, board.getValue(3, 0));
        board.moveDown();
        assertEquals(2, board.getValue(3, 0));
        board.setValue(2, 0, 2);
        board.moveDown();
        assertEquals(4, board.getValue(3, 0));
    }

    @Test
    void moveLeft() {
        assertEquals(0, board.getValue(0, 0));
        board.setValue(0, 1, 2);
        assertEquals(0, board.getValue(0, 0));
        board.moveLeft();
        assertEquals(2, board.getValue(0, 0));
        board.setValue(0, 1, 2);
        board.moveLeft();
        assertEquals(4, board.getValue(0, 0));
    }

    @Test
    void moveRight() {
        assertEquals(0, board.getValue(3, 3));
        board.setValue(3, 2, 2);
        assertEquals(0, board.getValue(3, 3));
        board.moveRight();
        assertEquals(2, board.getValue(3, 3));
        board.setValue(3, 2, 2);
        board.moveRight();
        assertEquals(4, board.getValue(3, 3));
    }

    @Test
    void isOver() {
        assertEquals(false, board.isOver());
        board.setValue(0, 0, 4);
        board.setValue(0, 1, 8);
        board.setValue(0, 2, 16);
        board.setValue(0, 3, 32);
        board.setValue(1, 0, 32);
        board.setValue(1, 1, 16);
        board.setValue(1, 2, 8);
        board.setValue(1, 3, 4);
        board.setValue(2, 0, 4);
        board.setValue(2, 1, 8);
        board.setValue(2, 2, 16);
        board.setValue(2, 3, 32);
        board.setValue(3, 0, 32);
        board.setValue(3, 1, 16);
        board.setValue(3, 2, 8);
        board.setValue(3, 3, 4);
        assertEquals(true, board.isOver());

        this.board = new Board();
        assertEquals(false, board.isOver());
        board.setValue(0, 0, 1024);
        board.setValue(0, 1, 1024);
        board.moveLeft();
        assertEquals(true, board.isOver());

    }
}