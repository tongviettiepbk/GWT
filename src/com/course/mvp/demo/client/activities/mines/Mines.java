package com.course.mvp.demo.client.activities.mines;

import com.course.mvp.demo.client.activities.mines.data.Data;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;

public class Mines {

	int finsh = 0;
	int x, y, cCol, cRow;
	boolean rep;
	private Canvas canvas;
	private Context2d ctx;
	private int width, height, sizeHeader;

	private final int NUM_IMAGES = 13;
	private int CELL_SIZE_W;
	private int CELL_SIZE_H;

	private final int COVER_FOR_CELL = 10;
	private final int MARK_FOR_CELL = 10;
	private final int EMPTY_CELL = 0;
	private final int MINE_CELL = 9;
	private final int COVERED_MINE_CELL = MINE_CELL + COVER_FOR_CELL;
	private final int MARKED_MINE_CELL = COVERED_MINE_CELL + MARK_FOR_CELL;

	private final int DRAW_MINE = 9;
	private final int DRAW_COVER = 10;
	private final int DRAW_MARK = 11;
	private final int DRAW_WRONG_MARK = 12;

	public static int N_MINES = 40;
	public static int N_ROWS = 16;
	public static int N_COLS = 16;
	private int[] field;
	private boolean inGame;
	private int mines_left;
	private Image[] img;
	final Data data = Data.IMPL;
	final MinesweeperViewGwtImpl view = new MinesweeperViewGwtImpl();

	private int all_cells;

	public Canvas getCanvas() {
		return this.canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Mines() {

	}

	public Mines(int width, int height, int sizeHeader) {
		canvas = Canvas.createIfSupported();
		ctx = canvas.getContext2d();
		canvas.setCoordinateSpaceWidth(width);
		canvas.setCoordinateSpaceHeight(height);
		this.width = width;
		this.height = height;
		this.sizeHeader = sizeHeader;
		img = new Image[NUM_IMAGES];
		img[0] = new Image(data.image0());
		img[1] = new Image(data.image1());
		img[2] = new Image(data.image2());
		img[3] = new Image(data.image3());
		img[4] = new Image(data.image4());
		img[5] = new Image(data.image5());
		img[6] = new Image(data.image6());
		img[7] = new Image(data.image7());
		img[8] = new Image(data.image8());
		img[9] = new Image(data.image9());
		img[10] = new Image(data.image10());
		img[11] = new Image(data.image11());
		img[12] = new Image(data.image12());
		newGame();
		paintComponent();
		canvas.addClickHandler(new Myclick());
		canvas.addTouchStartHandler(new touch());
	}

	private void newGame() {

		int current_col;

		int i = 0;
		int position = 0;
		int cell = 0;

		inGame = true;
		mines_left = N_MINES + 1;

		all_cells = N_ROWS * N_COLS;
		field = new int[all_cells];

		for (i = 0; i < all_cells; i++)
			field[i] = COVER_FOR_CELL;
		i = 0;
		while (i < N_MINES) {

			position = (int) (all_cells * Random.nextDouble());
			if ((position < all_cells)
					&& (field[position] != COVERED_MINE_CELL)) {
				current_col = position % N_COLS;
				field[position] = COVERED_MINE_CELL;
				i++;

				if (current_col > 0) {
					cell = position - 1 - N_COLS;
					if (cell >= 0)
						if (field[cell] != COVERED_MINE_CELL)
							field[cell] += 1;
					cell = position - 1;
					if (cell >= 0)
						if (field[cell] != COVERED_MINE_CELL)
							field[cell] += 1;

					cell = position + N_COLS - 1;
					if (cell < all_cells)
						if (field[cell] != COVERED_MINE_CELL)
							field[cell] += 1;
				}

				cell = position - N_COLS;
				if (cell >= 0)
					if (field[cell] != COVERED_MINE_CELL)
						field[cell] += 1;
				cell = position + N_COLS;
				if (cell < all_cells)
					if (field[cell] != COVERED_MINE_CELL)
						field[cell] += 1;

				if (current_col < (N_COLS - 1)) {
					cell = position - N_COLS + 1;
					if (cell >= 0)
						if (field[cell] != COVERED_MINE_CELL)
							field[cell] += 1;
					cell = position + N_COLS + 1;
					if (cell < all_cells)
						if (field[cell] != COVERED_MINE_CELL)
							field[cell] += 1;
					cell = position + 1;
					if (cell < all_cells)
						if (field[cell] != COVERED_MINE_CELL)
							field[cell] += 1;
				}
			}
		}
	}

	public void find_empty_cells(int j) {

		int current_col = j % N_COLS;
		int cell;

		if (current_col > 0) {
			cell = j - N_COLS - 1;
			if (cell >= 0)
				if (field[cell] > MINE_CELL) {
					field[cell] -= COVER_FOR_CELL;
					if (field[cell] == EMPTY_CELL)
						find_empty_cells(cell);
				}

			cell = j - 1;
			if (cell >= 0)
				if (field[cell] > MINE_CELL) {
					field[cell] -= COVER_FOR_CELL;
					if (field[cell] == EMPTY_CELL)
						find_empty_cells(cell);
				}

			cell = j + N_COLS - 1;
			if (cell < all_cells)
				if (field[cell] > MINE_CELL) {
					field[cell] -= COVER_FOR_CELL;
					if (field[cell] == EMPTY_CELL)
						find_empty_cells(cell);
				}
		}

		cell = j - N_COLS;
		if (cell >= 0)
			if (field[cell] > MINE_CELL) {
				field[cell] -= COVER_FOR_CELL;
				if (field[cell] == EMPTY_CELL)
					find_empty_cells(cell);
			}

		cell = j + N_COLS;
		if (cell < all_cells)
			if (field[cell] > MINE_CELL) {
				field[cell] -= COVER_FOR_CELL;
				if (field[cell] == EMPTY_CELL)
					find_empty_cells(cell);
			}

		if (current_col < (N_COLS - 1)) {
			cell = j - N_COLS + 1;
			if (cell >= 0)
				if (field[cell] > MINE_CELL) {
					field[cell] -= COVER_FOR_CELL;
					if (field[cell] == EMPTY_CELL)
						find_empty_cells(cell);
				}

			cell = j + N_COLS + 1;
			if (cell < all_cells)
				if (field[cell] > MINE_CELL) {
					field[cell] -= COVER_FOR_CELL;
					if (field[cell] == EMPTY_CELL)
						find_empty_cells(cell);
				}

			cell = j + 1;
			if (cell < all_cells)
				if (field[cell] > MINE_CELL) {
					field[cell] -= COVER_FOR_CELL;
					if (field[cell] == EMPTY_CELL)
						find_empty_cells(cell);
				}
		}

	}

	public void paintComponent() {
		int cell = 0;
		int uncover = 0;
		canvas.setCoordinateSpaceHeight(height);
		canvas.setCoordinateSpaceWidth(width);
		CELL_SIZE_W = (int) width / (N_COLS);
		CELL_SIZE_H = (int) height / (N_ROWS);
		for (int i = 0; i < N_ROWS; i++) {
			for (int j = 0; j < N_COLS; j++) {

				cell = field[(i * N_COLS) + j];

				if (inGame && cell == MINE_CELL)
					inGame = false;

				if (!inGame) {
					if (cell == COVERED_MINE_CELL) {
						cell = DRAW_MINE;
					} else if (cell == MARKED_MINE_CELL) {
						cell = DRAW_MARK;
					} else if (cell > COVERED_MINE_CELL) {
						cell = DRAW_WRONG_MARK;
					} else if (cell > MINE_CELL) {
						cell = DRAW_COVER;
					}

				} else {
					if (cell > COVERED_MINE_CELL)
						cell = DRAW_MARK;
					else if (cell > MINE_CELL) {
						cell = DRAW_COVER;
						uncover++;
					}
				}
				ctx.drawImage(ImageElement.as(img[cell].getElement()), j
						* CELL_SIZE_W, i * CELL_SIZE_H, CELL_SIZE_W,
						CELL_SIZE_H);
			}
		}

		if (uncover == 0 && inGame) {
			inGame = false;
			Dialogs.alert("WOW", "Chúc mừng bạn đã chiến thắng", null);
		} else if (!inGame) {
			Dialogs.alert("DEFEAT", "Bạn đã thất bại", null);
		}
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						width = Window.getClientWidth();
						height = Window.getClientHeight() - sizeHeader;
						paintComponent();
					}
				});
			}
		});
	}

	class Myclick implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			event.getNativeEvent();
			event.getNativeEvent();
			event.getNativeEvent();
			if (event.getNativeButton() == NativeEvent.BUTTON_LEFT) {
				x = event.getClientX();
				y = event.getClientY() - sizeHeader;
				cCol = (int) (x / CELL_SIZE_W);
				cRow = (int) (y / (CELL_SIZE_H));
				rep = false;
				if (!inGame) {
					newGame();
					paintComponent();
				}

			}
			if ((x < (N_COLS) * CELL_SIZE_W) && (y < (N_ROWS) * CELL_SIZE_H)) {

				event.getNativeEvent();
				event.getNativeEvent();
				if (event.getNativeButton() == NativeEvent.BUTTON_LEFT) {
					if (field[(cRow * N_COLS) + cCol] > MINE_CELL) {
						rep = true;

						if (field[(cRow * N_COLS) + cCol] <= COVERED_MINE_CELL) {
							if (mines_left > 0) {
								field[(cRow * N_COLS) + cCol] += MARK_FOR_CELL;
								mines_left--;
							}

						} else {

							field[(cRow * N_COLS) + cCol] -= MARK_FOR_CELL;
							mines_left++;
							if ((field[(cRow * N_COLS) + cCol] > MINE_CELL)
									&& (field[(cRow * N_COLS) + cCol] < MARKED_MINE_CELL)) {

								field[(cRow * N_COLS) + cCol] -= COVER_FOR_CELL;
								rep = true;

								if (field[(cRow * N_COLS) + cCol] == MINE_CELL)
									inGame = false;
								if (field[(cRow * N_COLS) + cCol] == EMPTY_CELL)
									find_empty_cells((cRow * N_COLS) + cCol);
							}
						}
					}

				}

				if (rep)
					paintComponent();

			}
		}

	}

	class touch implements TouchStartHandler {

		@Override
		public void onTouchStart(TouchStartEvent event) {
			x = event.getNativeEvent().getClientX();
			y = event.getNativeEvent().getClientY() - sizeHeader;
			cCol = x / CELL_SIZE_W;
			cRow = y / CELL_SIZE_H;
			rep = false;
			if (!inGame) {
				newGame();
				paintComponent();
			}
			if ((x < N_COLS * CELL_SIZE_W) && (y < N_ROWS * CELL_SIZE_H)) {
				if (event.getTargetTouches().length() != 0) {

					if (field[(cRow * N_COLS) + cCol] > MINE_CELL) {
						rep = true;

						if (field[(cRow * N_COLS) + cCol] <= COVERED_MINE_CELL) {
							if (mines_left > 0) {
								field[(cRow * N_COLS) + cCol] += MARK_FOR_CELL;
								mines_left--;
							}
						} else {

							field[(cRow * N_COLS) + cCol] -= MARK_FOR_CELL;
							mines_left++;
							if ((field[(cRow * N_COLS) + cCol] > MINE_CELL)
									&& (field[(cRow * N_COLS) + cCol] < MARKED_MINE_CELL)) {

								field[(cRow * N_COLS) + cCol] -= COVER_FOR_CELL;
								rep = true;

								if (field[(cRow * N_COLS) + cCol] == MINE_CELL)
									inGame = false;
								if (field[(cRow * N_COLS) + cCol] == EMPTY_CELL)
									find_empty_cells((cRow * N_COLS) + cCol);
							}
						}
					}

				}
				if (rep)
					paintComponent();
			}
		}

	}
}
