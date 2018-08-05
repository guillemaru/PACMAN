package Projet;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Principal extends Application {

	int isPacman = 0;

	public void start(Stage stage) throws Exception {

		StackPane main = new StackPane();
		GridPane grid = new GridPane();
		Pane pacpane = new Pane();

		Scene scene = new Scene(main, 800, 400);

		// Parameters
		int squarelength = 20;
		Color pointcolor = Color.BLUE;
		Color backgroundcolor = Color.BLACK;

		//  These two lines for enabling to resize the scene and the pacman
		DoubleBinding dpi = scene.heightProperty().divide(20);
		DoubleBinding dpj = scene.widthProperty().divide(40);

		// This part is for the explanations to the user that will be in the bottom part of the scene
		
		AnchorPane explications = new AnchorPane();
		HBox explications_hbox = new HBox(10);

		Rectangle explic_background = new Rectangle(0, 380, 800, 20);
		explic_background.setFill(Color.YELLOW);
		explic_background.widthProperty().bind(scene.widthProperty());
		explic_background.heightProperty().bind(dpi); // to regulate the height of the yellow rectangle

		Text explic_text = new Text(5, 400,"Bienvenue sur le jeu Pacman, " 
		+ "voulez-vous utiliser le scénario par défaut?");

		Button oui = new Button("OUI");
		Button non = new Button("NON");

		// To redefine the scene style (specially for the buttons)
		String css = this.getClass().getResource("buttonType.css").toExternalForm();
		scene.getStylesheets().add(css);

		explications_hbox.getChildren().addAll(explic_text, oui, non);
		explications_hbox.setAlignment(Pos.BOTTOM_LEFT);

		explications.getChildren().addAll(explic_background, explications_hbox);

		AnchorPane.setLeftAnchor(explic_background, 0.0);
		AnchorPane.setBottomAnchor(explic_background, 0.0);
		AnchorPane.setBottomAnchor(explications_hbox, 0.0);
		AnchorPane.setTopAnchor(explications_hbox, 380.0);

		// THIS IS THE LEVEL BY DEFAULT
		Grid level_1 = new Grid(squarelength);

		// Construction of the walls
		level_1.addHwall(2, 12, 8, 1);
		level_1.addHwall(2, 21, 7, 1);

		level_1.addHwall(4, 12, 8, 1);
		level_1.addHwall(4, 21, 7, 1);

		level_1.addHwall(15, 12, 8, 1);
		level_1.addHwall(15, 21, 7, 1);

		level_1.addHwall(17, 12, 8, 1);
		level_1.addHwall(17, 21, 7, 1);

		level_1.addVwall(1, 10, 4, -1);
		level_1.addVwall(1, 29, 4, -1);

		level_1.addVwall(18, 10, 5, 1);
		level_1.addVwall(18, 29, 5, 1);

		level_1.addHwall(6, 8, 3, 1);
		level_1.addHwall(8, 8, 3, 1);
		level_1.addHwall(10, 8, 3, 1);
		level_1.addHwall(12, 8, 3, 1);
		level_1.addHwall(6, 29, 3, 1);
		level_1.addHwall(8, 29, 3, 1);
		level_1.addHwall(10, 29, 3, 1);
		level_1.addHwall(12, 29, 3, 1);

		level_1.addHwall(2, 2, 7, 1);
		level_1.addVwall(3, 2, 7, -1);
		level_1.addVwall(11, 2, 7, -1);
		level_1.addHwall(17, 3, 6, 1);

		level_1.addHwall(4, 4, 5, 1);
		level_1.addVwall(5, 4, 5, -1);
		level_1.addVwall(11, 4, 5, -1);
		level_1.addHwall(15, 4, 5, 1);

		level_1.addVwall(6, 6, 4, -1);
		level_1.addVwall(11, 6, 3, -1);

		level_1.addVwall(6, 33, 4, -1);
		level_1.addVwall(11, 33, 3, -1);

		level_1.addHwall(2, 31, 7, 1);
		level_1.addVwall(3, 37, 7, -1);
		level_1.addVwall(11, 37, 7, -1);
		level_1.addHwall(17, 31, 6, 1);

		level_1.addHwall(4, 31, 5, 1);
		level_1.addVwall(5, 35, 5, -1);
		level_1.addVwall(11, 35, 5, -1);
		level_1.addHwall(15, 31, 4, 1);

		level_1.addHwall(6, 12, 6, 1);
		level_1.addHwall(6, 22, 6, 1);
		level_1.addVwall(7, 12, 7, -1);
		level_1.addVwall(7, 27, 7, -1);
		level_1.addHwall(13, 13, 14, 1);

		level_1.addHwall(13, 8, 1, 1);
		level_1.addHwall(13, 31, 1, 1);

		// Filling of the grid (GridPane)
		ArrayList<Point> arraypoints = new ArrayList<Point>();
		for (int i = 0; i < Math.round(400 / squarelength); i++) {
			for (int j = 0; j < Math.round(800 / squarelength); j++) {
				if (level_1.get(i, j) == 1) {
					Pane rect = new Pane();
					rect.setStyle("-fx-background-color: black;"); // this line is tosolve the problem of the grey background when I add the buttons

					Image img = new Image("wallWhite.png");
					ImageView imgv = new ImageView(img);

					// adjust the image of the walls to the scene size
					imgv.fitHeightProperty().bind(dpi);
					imgv.fitWidthProperty().bind(dpj);

					rect.getChildren().add(imgv);
					rect.setPrefSize(100, 100);
					grid.getChildren().add(rect);
					GridPane.setConstraints(rect, j, i);
				} else {
					if (i > 5 && i < 13 && j > 12 && j < 27) {
						// leaving the central part without pac-gums

						Pane rect = new Pane();
						rect.setStyle("-fx-background-color: black;"); // this line is to solve the problem of the grey background when I add the buttons
						grid.getChildren().add(rect);
						GridPane.setConstraints(rect, j, i);
					} else {
						//Creation of the pac-gums
						Pane rect = new Pane();
						rect.setStyle("-fx-background-color: black;"); // this line is to solve the problem of the grey background when I add the buttons
						grid.getChildren().add(rect);
						GridPane.setConstraints(rect, j, i);

						Point p = new Point(j, i, pointcolor, squarelength);
						arraypoints.add(p);
						pacpane.getChildren().add(p.getCircle());

						p.getCircle().radiusProperty().bind(dpi.divide(4));
						p.getCircle().centerYProperty().bind(dpi.divide(2).add(dpi.multiply(i)));
						p.getCircle().centerXProperty().bind(dpj.divide(2).add(dpj.multiply(j)));

					}
				}
			}
		}

		// CREATION OF PACMAN
		Pacman pacman = new Pacman(16, 10, squarelength);
		pacpane.getChildren().add(pacman.getImg());

		// ADJUST THE PARAMETERS TO ALLOW THE GAME TO BE RESIZABLE
		pacman.getImg().fitHeightProperty().bind(dpi);
		pacman.getImg().fitWidthProperty().bind(dpj);

		pacman.getsizeDP().bind(dpi);
		pacman.getsceneWDP().bind(scene.widthProperty());
		pacman.getsceneHDP().bind(scene.heightProperty());

		pacman.getImg().xProperty()
				.bind(pacman.getCircle().centerXProperty().subtract(scene.widthProperty().divide(80)));
		pacman.getImg().yProperty()
				.bind(pacman.getCircle().centerYProperty().subtract(scene.widthProperty().divide(80)));

		// Ghosts creation
		ArrayList<Ghost> arrayghost = new ArrayList<Ghost>();
		Ghost ghost_1 = new Ghost(1, 1, squarelength);
		arrayghost.add(ghost_1);
		Ghost ghost_2 = new Ghost(38, 1, squarelength);
		arrayghost.add(ghost_2);
		Ghost ghost_3 = new Ghost(1, 18, squarelength);
		arrayghost.add(ghost_3);
		Ghost ghost_4 = new Ghost(38, 18, squarelength);
		arrayghost.add(ghost_4);
		
		//To show the ghosts
		for (Ghost ghost : arrayghost) {
			ghost.getImg().fitHeightProperty().bind(dpi);
			ghost.getImg().fitWidthProperty().bind(dpj);

			ghost.getsizeDP().bind(dpi);
			ghost.getsceneWDP().bind(scene.widthProperty());
			ghost.getsceneHDP().bind(scene.heightProperty());

			ghost.getImg().xProperty()
					.bind(ghost.getCircle().centerXProperty().subtract(scene.widthProperty().divide(80)));
			ghost.getImg().yProperty()
					.bind(ghost.getCircle().centerYProperty().subtract(scene.widthProperty().divide(80)));

			pacpane.getChildren().add(ghost.getImg());

		}

		// IF THE USER DECIDES TO PLAY WITH THE DEFAULT SCENARIO...
		Timeline animation = new Timeline();
		oui.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				// WAIT 3 SECONDS UNTIL THE GAME STARTS
				explications_hbox.getChildren().removeAll(explic_text, oui, non);
				DefaultStartTask deftask = new DefaultStartTask();
				Text deftext = new Text();
				explications_hbox.getChildren().add(deftext);
				deftext.textProperty().bind(deftask.textprop);
				new Thread(deftask).start();

				// THE ANIMATION STARTS
				animation.setCycleCount(Animation.INDEFINITE);
				animation.getKeyFrames().add(new KeyFrame(new Duration(100.0), new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event2) {
						//To set the pacman direction
						scene.setOnKeyPressed(event -> {
							if (event.getCode() == KeyCode.RIGHT) { 
								pacman.setWantedDirection(2);
							}
							if (event.getCode() == KeyCode.DOWN) {
								pacman.setWantedDirection(3);
							}
							if (event.getCode() == KeyCode.LEFT) {
								pacman.setWantedDirection(4);
							}
							if (event.getCode() == KeyCode.UP) {
								pacman.setWantedDirection(1);
							}
						});
						//To set the pacman movement
						if (pacman.getWantedDirection() == 1
								&& level_1.get(pacman.getYPosition() - 1, pacman.getXPosition()) == 0) {
							pacman.setMovement(1);
						}
						if (pacman.getWantedDirection() == 2
								&& level_1.get(pacman.getYPosition(), pacman.getXPosition() + 1) == 0) {
							pacman.setMovement(2);
						}
						if (pacman.getWantedDirection() == 3
								&& level_1.get(pacman.getYPosition() + 1, pacman.getXPosition()) == 0) {
							pacman.setMovement(3);
						}
						if (pacman.getWantedDirection() == 4
								&& level_1.get(pacman.getYPosition(), pacman.getXPosition() - 1) == 0) {
							pacman.setMovement(4);
						}
						//The pacman move every 200 ms (every two steps)
						if (pacman.getTime() % 2 == 0) {
							if (pacman.getMovement() == 1
									&& level_1.get(pacman.getYPosition() - 1, pacman.getXPosition()) == 1) {
								pacman.setMovement(0);
							}
							if (pacman.getMovement() == 2
									&& level_1.get(pacman.getYPosition(), pacman.getXPosition() + 1) == 1) {
								pacman.setMovement(0);
							}
							if (pacman.getMovement() == 3
									&& level_1.get(pacman.getYPosition() + 1, pacman.getXPosition()) == 1) {
								pacman.setMovement(0);
							}
							if (pacman.getMovement() == 4
									&& level_1.get(pacman.getYPosition(), pacman.getXPosition() - 1) == 1) {
								pacman.setMovement(0);
							}

							if (deftext.textProperty().get().equals("The game has started, good luck!")) {
								pacman.move();
							}

							//To eat the pac-gums
							for (Point p : arraypoints) {
								Circle circ = p.getCircle();
								if (pacman.getCircle().intersects(circ.getBoundsInParent())) {
									circ.setFill(backgroundcolor);
								}
							}
						}
						
						//The ghosts move every 300 ms (every three steps)
						if (pacman.getTime() % 3 == 0) {
							for (Ghost ghost : arrayghost) {
								int[] tabrange = new int[4];
								tabrange[0] = ghost.getYPosition() - pacman.getYPosition();
								tabrange[1] = pacman.getXPosition() - ghost.getXPosition();
								tabrange[2] = pacman.getYPosition() - ghost.getYPosition();
								tabrange[3] = ghost.getXPosition() - pacman.getXPosition();

								// Beginning of AI
								if (ghost.getMovement() == 0) {
									if (level_1.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 0) {
										ghost.setMovement(1);
									} else if (level_1.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 0) {
										ghost.setMovement(2);
									} else if (level_1.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 0) {
										ghost.setMovement(3);
									} else if (level_1.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 0) {
										ghost.setMovement(4);
									}
								}

								if (ghost.getMovement() == 1) {
									if (level_1.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 0
											&& level_1.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 1) {
										if (level_1.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 1
												|| tabrange[0] < 0) {
											ghost.setMovement(2);
										} else if (tabrange[1] >= 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(2);
											}
										}
									} else if (level_1.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 0
											&& level_1.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 1) {
										if (level_1.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 1
												|| tabrange[0] < 0) {
											ghost.setMovement(4);
										} else if (tabrange[3] >= 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(4);
											}
										}
									} else if (level_1.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 0
											&& level_1.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 0) {
										if (level_1.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 1
												|| tabrange[0] < 0) {
											if (tabrange[1] >= 0) {
												ghost.setMovement(2);
											} else {
												ghost.setMovement(4);
											}
										} else if (tabrange[1] >= 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(2);
											}
										} else if (tabrange[3] > 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(4);
											}
										}
									} else if (level_1.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 1) {
										ghost.setMovement(3);
									}
								}

								if (ghost.getMovement() == 2) {
									if (level_1.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 0
											&& level_1.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 1) {
										if (level_1.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 1
												|| tabrange[1] < 0) {
											ghost.setMovement(3);
										} else if (tabrange[2] >= 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(3);
											}
										}
									} else if (level_1.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 0
											&& level_1.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 1) {
										if (level_1.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 1
												|| tabrange[1] < 0) {
											ghost.setMovement(1);
										} else if (tabrange[0] >= 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(1);
											}
										}
									} else if (level_1.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 0
											&& level_1.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 0) {
										if (level_1.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 1
												|| tabrange[1] < 0) {
											if (tabrange[0] >= 0) {
												ghost.setMovement(1);
											} else {
												ghost.setMovement(3);
											}
										} else if (tabrange[0] >= 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(1);
											}
										} else if (tabrange[2] > 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(3);
											}
										}
									} else if (level_1.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 1) {
										ghost.setMovement(4);
									}
								}

								if (ghost.getMovement() == 3) {
									if (level_1.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 0
											&& level_1.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 1) {
										if (level_1.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 1
												|| tabrange[2] < 0) {
											ghost.setMovement(2);
										} else if (tabrange[1] >= 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(2);
											}
										}
									} else if (level_1.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 0
											&& level_1.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 1) {
										if (level_1.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 1
												|| tabrange[2] < 0) {
											ghost.setMovement(4);
										} else if (tabrange[3] >= 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(4);
											}
										}
									} else if (level_1.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 0
											&& level_1.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 0) {
										if (level_1.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 1
												|| tabrange[2] < 0) {
											if (tabrange[1] >= 0) {
												ghost.setMovement(2);
											} else {
												ghost.setMovement(4);
											}
										} else if (tabrange[1] >= 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(2);
											}
										} else if (tabrange[3] > 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(4);
											}
										}
									} else if (level_1.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 1) {
										ghost.setMovement(1);
									}
								}

								if (ghost.getMovement() == 4) {
									if (level_1.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 0
											&& level_1.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 1) {
										if (level_1.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 1
												|| tabrange[3] < 0) {
											ghost.setMovement(3);
										} else if (tabrange[2] >= 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(3);
											}
										}
									} else if (level_1.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 0
											&& level_1.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 1) {
										if (level_1.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 1
												|| tabrange[3] < 0) {
											ghost.setMovement(1);
										} else if (tabrange[0] >= 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(1);
											}
										}
									} else if (level_1.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 0
											&& level_1.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 0) {
										if (level_1.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 1
												|| tabrange[3] < 0) {
											if (tabrange[0] >= 0) {
												ghost.setMovement(1);
											} else {
												ghost.setMovement(3);
											}
										} else if (tabrange[0] >= 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(1);
											}
										} else if (tabrange[2] > 0) {
											int a = (int) Math.floor(Math.random() * 2);
											if (a == 0) {
												ghost.setMovement(3);
											}
										}
									} else if (level_1.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 1) {
										ghost.setMovement(2);
									}
								}

							}
							for (Ghost ghost : arrayghost) {
								if (deftext.textProperty().get().equals("The game has started, good luck!")) {
									ghost.move();
								}
							}
						}
						// End of AI
						//When you lose (if the pacman is caught by a ghost)
						for (Ghost ghost : arrayghost) {
							if (pacman.getXPosition() == ghost.getXPosition()
									&& pacman.getYPosition() == ghost.getYPosition()) {
								animation.stop();
								Timeline animation2 = new Timeline();
								animation2.getKeyFrames().addAll(
										new KeyFrame(new Duration(0.0), new KeyValue(pacpane.opacityProperty(), 1.0)),
										new KeyFrame(new Duration(2000.0),
												new KeyValue(pacpane.opacityProperty(), 0.0, Interpolator.LINEAR)));
								animation2.play();
							}
						}
						pacman.addTime();
					}
				}));
				animation.play();
			}
		});

		// IF THE USER DECIDES TO BUILD ANOTHER SCENARIO...
		non.setOnMouseClicked(new EventHandler<MouseEvent>() { // press the button NON
			@Override
			public void handle(MouseEvent event3) {

				main.getChildren().removeAll(grid, pacpane, explications); // change the main

				// Build new grid full of blue circles
				GridPane gridConstruction = new GridPane();
				Pane pacpaneConstruction = new Pane();

				Grid levelConstruction = new Grid(squarelength);

				// Filling of the grid
				ArrayList<Point> arraypoints2 = new ArrayList<Point>();
				for (int i = 0; i < Math.round(400 / squarelength); i++) {
					for (int j = 0; j < Math.round(800 / squarelength); j++) {
						if (levelConstruction.get(i, j) == 1) { // Replace the
																// EDGES with
																// walls
							Pane rect = new Pane();

							rect.setStyle("-fx-background-color: black;"); 

							Image img = new Image("wallWhite.png");
							ImageView imgv = new ImageView(img);

							imgv.fitHeightProperty().bind(dpi);
							imgv.fitWidthProperty().bind(dpj);

							rect.getChildren().add(imgv);
							rect.setPrefSize(100, 100);
							gridConstruction.getChildren().add(rect);
							GridPane.setConstraints(rect, j, i);

						} else { // the rest is for blue circles
							Pane rect = new Pane();
							rect.setStyle("-fx-background-color: black;");
							gridConstruction.getChildren().add(rect);
							GridPane.setConstraints(rect, j, i);

							Point p = new Point(j, i, pointcolor, squarelength);
							arraypoints2.add(p);
							pacpaneConstruction.getChildren().add(p.getCircle());

							p.getCircle().radiusProperty().bind(dpi.divide(4));
							p.getCircle().centerYProperty().bind(dpi.divide(2).add(dpi.multiply(i)));
							p.getCircle().centerXProperty().bind(dpj.divide(2).add(dpj.multiply(j)));

						}
					}
				}

				// add the changes to the main
				main.getChildren().addAll(gridConstruction, explications, pacpaneConstruction);

				// new explanations for the user
				explications_hbox.getChildren().removeAll(oui, non);
				explic_text.textProperty()
						.setValue("Construisez le scénario" + ". Sélectionnez un cercle bleu pour le convertir en mur"
								+ " (pas possible d'annuler!). Appuyez sur ESPACE quand vous avez fini.");

				// WHEN THE USER CLICKS A CIRCLE, IT IS CHANGED INTO A WALL
				for (Point p : arraypoints2) {
					Circle circ = p.getCircle();
					circ.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event4) {
							pacpaneConstruction.getChildren().remove(p.getCircle()); // remove the circle												
							arraypoints2.remove(p);
							Pane rect = new Pane();

							rect.setStyle("-fx-background-color: black;");
							
							Image img = new Image("wallWhite.png");
							ImageView imgv = new ImageView(img);

							imgv.fitHeightProperty().bind(dpi);
							imgv.fitWidthProperty().bind(dpj);

							rect.getChildren().add(imgv);
							rect.setPrefSize(100, 100);
							gridConstruction.getChildren().add(rect);
							GridPane.setConstraints(rect, p.getXpostion(), p.getYpostion());
							levelConstruction.add(p.getYpostion(), p.getXpostion());
						}
					});

				}
				// WHEN THE USER IS FINISHED WITH THE WALLS
				
				ArrayList<Ghost> arrayghost2 = new ArrayList<Ghost>();
				scene.setOnKeyPressed(event12 -> {
					if (event12.getCode() == KeyCode.SPACE) {

						explic_text.textProperty().setValue("Placez les fantômes où vous voulez,"
								+ " appuyez sur un cercle bleu. Appuyez sur ENTRER quand vous avez fini.");

						for (Point p : arraypoints2) {
							Circle circ = p.getCircle();
							circ.setOnMouseClicked(new EventHandler<MouseEvent>() { // the user clicks on a circle to put a ghost
								@Override
								public void handle(MouseEvent event4) {
									Ghost ghost = new Ghost(p.getXpostion(), p.getYpostion(), squarelength);
									arrayghost2.add(ghost);
									ghost.getImg().fitHeightProperty().bind(dpi);
									ghost.getImg().fitWidthProperty().bind(dpj);

									ghost.getsizeDP().bind(dpi);
									ghost.getsceneWDP().bind(scene.widthProperty());
									ghost.getsceneHDP().bind(scene.heightProperty());

									ghost.getImg().xProperty().bind(ghost.getCircle().centerXProperty()
											.subtract(scene.widthProperty().divide(80)));
									ghost.getImg().yProperty().bind(ghost.getCircle().centerYProperty()
											.subtract(scene.widthProperty().divide(80)));

									pacpaneConstruction.getChildren().add(ghost.getImg());
								}
							});
						}

					}
					if (event12.getCode() == KeyCode.ENTER) {
						explic_text.textProperty().setValue("Placez le pacman où vous voulez,"
								+ " appuyez sur un cercle bleu. Le jeu commencera immédiatement.");
						for (Point p : arraypoints2) {
							Circle circ = p.getCircle();
							circ.setOnMouseClicked(new EventHandler<MouseEvent>() { // the user clicks on a circle to put the pacman
								@Override
								public void handle(MouseEvent event4) {

									pacpaneConstruction.getChildren().remove(p.getCircle());

									// CREATION OF PACMAN
									int px = p.getXpostion();
									int py = p.getYpostion();
									Pacman pacman2 = new Pacman(px, py, squarelength);

									// ADJUST THE PARAMETERS TO ALLOW THE GAME
									// TO BE RESIZABLE
									pacman2.getImg().fitHeightProperty().bind(dpi);
									pacman2.getImg().fitWidthProperty().bind(dpj);

									pacman2.getsizeDP().bind(dpi);
									pacman2.getsceneWDP().bind(scene.widthProperty());
									pacman2.getsceneHDP().bind(scene.heightProperty());

									pacman2.getImg().xProperty().bind(pacman2.getCircle().centerXProperty()
											.subtract(scene.widthProperty().divide(80)));
									pacman2.getImg().yProperty().bind(pacman2.getCircle().centerYProperty()
											.subtract(scene.widthProperty().divide(80)));

									pacpaneConstruction.getChildren().add(pacman2.getImg());
									
									arraypoints2.remove(p);

									// PREPARE THE START OF THE GAME

									// WAIT 3 SECONDS UNTIL THE GAME STARTS
									explications_hbox.getChildren().removeAll(explic_text, oui, non);
									DefaultStartTask deftask = new DefaultStartTask();
									Text deftext = new Text();
									explications_hbox.getChildren().add(deftext);
									deftext.textProperty().bind(deftask.textprop);
									new Thread(deftask).start();

									// THE ANIMATION STARTS
									// We use the same code as in the first part (DEFAULT SCENARIO)
									animation.setCycleCount(Animation.INDEFINITE);
									animation.getKeyFrames().add(new KeyFrame(new Duration(100.0), new EventHandler<ActionEvent>() {
										public void handle(ActionEvent event2) {
											scene.setOnKeyPressed(event -> {
												if (event.getCode() == KeyCode.RIGHT) { 																	// level
													pacman2.setWantedDirection(2);
												}
												if (event.getCode() == KeyCode.DOWN) {
													pacman2.setWantedDirection(3);
												}
												if (event.getCode() == KeyCode.LEFT) {
													pacman2.setWantedDirection(4);
												}
												if (event.getCode() == KeyCode.UP) {
													pacman2.setWantedDirection(1);
												}
											});
											if (pacman2.getWantedDirection() == 1 && levelConstruction.get(pacman2.getYPosition() - 1, pacman2.getXPosition()) == 0) {
												pacman2.setMovement(1);
											}
											if (pacman2.getWantedDirection() == 2 && levelConstruction.get(pacman2.getYPosition(), pacman2.getXPosition() + 1) == 0) {
												pacman2.setMovement(2);
											}
											if (pacman2.getWantedDirection() == 3 && levelConstruction.get(pacman2.getYPosition() + 1, pacman2.getXPosition()) == 0) {
												pacman2.setMovement(3);
											}
											if (pacman2.getWantedDirection() == 4 && levelConstruction.get(pacman2.getYPosition(), pacman2.getXPosition() - 1) == 0) {
												pacman2.setMovement(4);
											}
											if (pacman2.getTime() % 2 == 0) {
												if (pacman2.getMovement() == 1
														&& levelConstruction.get(pacman2.getYPosition() - 1, pacman2.getXPosition()) == 1) { 
													pacman2.setMovement(0);
												}
												if (pacman2.getMovement() == 2
														&& levelConstruction.get(pacman2.getYPosition(), pacman2.getXPosition() + 1) == 1) { 
													pacman2.setMovement(0);
												}
												if (pacman2.getMovement() == 3
														&& levelConstruction.get(pacman2.getYPosition() + 1, pacman2.getXPosition()) == 1) {
													pacman2.setMovement(0);
												}
												if (pacman2.getMovement() == 4
														&& levelConstruction.get(pacman2.getYPosition(), pacman2.getXPosition() - 1) == 1) { 
													pacman2.setMovement(0);
												}
												
												if (deftext.textProperty().get().equals("The game has started, good luck!")) {
													pacman2.move();
												}

												for (Point p : arraypoints2) {
													Circle circ = p.getCircle();
													if (pacman2.getCircle().intersects(circ.getBoundsInParent())) {
														circ.setFill(backgroundcolor);
													}
												}
											}
											if (pacman2.getTime() % 3 == 0) {
												for (Ghost ghost : arrayghost2) {
													int[] tabrange = new int[4];
													tabrange[0] = ghost.getYPosition() - pacman2.getYPosition();
													tabrange[1] = pacman2.getXPosition() - ghost.getXPosition();
													tabrange[2] = pacman2.getYPosition() - ghost.getYPosition();
													tabrange[3] = ghost.getXPosition() - pacman2.getXPosition();
													
													if (ghost.getMovement() == 0) {
														if (levelConstruction.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 0) {
															ghost.setMovement(1);
														} else if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 0) {
															ghost.setMovement(2);
														} else if (levelConstruction.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 0) {
															ghost.setMovement(3);
														} else if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 0) {
															ghost.setMovement(4);
														}
													}

													if (ghost.getMovement() == 1) {
														if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 0
																&& level_1.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 1) {
															if (levelConstruction.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 1
																	|| tabrange[0] < 0) {
																ghost.setMovement(2);
															} else if (tabrange[1] >= 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(2);
																}
															}
														} else if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 0
																&& levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 1) {
															if (levelConstruction.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 1
																	|| tabrange[0] < 0) {
																ghost.setMovement(4);
															} else if (tabrange[3] >= 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(4);
																}
															}
														} else if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 0
																&& levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 0) {
															if (levelConstruction.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 1
																	|| tabrange[0] < 0) {
																if (tabrange[1] >= 0) {
																	ghost.setMovement(2);
																} else {
																	ghost.setMovement(4);
																}
															} else if (tabrange[1] >= 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(2);
																}
															} else if (tabrange[3] > 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(4);
																}
															}
														} else if (levelConstruction.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 1) {
															ghost.setMovement(3);
														}
													}

													if (ghost.getMovement() == 2) {
														if (levelConstruction.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 0
																&& levelConstruction.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 1) {
															if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 1
																	|| tabrange[1] < 0) {
																ghost.setMovement(3);
															} else if (tabrange[2] >= 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(3);
																}
															}
														} else if (levelConstruction.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 0
																&& levelConstruction.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 1) {
															if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 1
																	|| tabrange[1] < 0) {
																ghost.setMovement(1);
															} else if (tabrange[0] >= 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(1);
																}
															}
														} else if (levelConstruction.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 0
																&& levelConstruction.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 0) {
															if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 1
																	|| tabrange[1] < 0) {
																if (tabrange[0] >= 0) {
																	ghost.setMovement(1);
																} else {
																	ghost.setMovement(3);
																}
															} else if (tabrange[0] >= 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(1);
																}
															} else if (tabrange[2] > 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(3);
																}
															}
														} else if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 1) {
															ghost.setMovement(4);
														}
													}

													if (ghost.getMovement() == 3) {
														if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 0
																&& levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 1) {
															if (levelConstruction.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 1
																	|| tabrange[2] < 0) {
																ghost.setMovement(2);
															} else if (tabrange[1] >= 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(2);
																}
															}
														} else if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 0
																&& levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 1) {
															if (levelConstruction.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 1
																	|| tabrange[2] < 0) {
																ghost.setMovement(4);
															} else if (tabrange[3] >= 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(4);
																}
															}
														} else if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 0
																&& levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() + 1) == 0) {
															if (levelConstruction.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 1
																	|| tabrange[2] < 0) {
																if (tabrange[1] >= 0) {
																	ghost.setMovement(2);
																} else {
																	ghost.setMovement(4);
																}
															} else if (tabrange[1] >= 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(2);
																}
															} else if (tabrange[3] > 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(4);
																}
															}
														} else if (levelConstruction.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 1) {
															ghost.setMovement(1);
														}
													}

													if (ghost.getMovement() == 4) {
														if (levelConstruction.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 0
																&& levelConstruction.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 1) {
															if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 1
																	|| tabrange[3] < 0) {
																ghost.setMovement(3);
															} else if (tabrange[2] >= 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(3);
																}
															}
														} else if (levelConstruction.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 0
																&& levelConstruction.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 1) {
															if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 1
																	|| tabrange[3] < 0) {
																ghost.setMovement(1);
															} else if (tabrange[0] >= 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(1);
																}
															}
														} else if (levelConstruction.get(ghost.getYPosition() + 1, ghost.getXPosition()) == 0
																&& levelConstruction.get(ghost.getYPosition() - 1, ghost.getXPosition()) == 0) {
															if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 1
																	|| tabrange[3] < 0) {
																if (tabrange[0] >= 0) {
																	ghost.setMovement(1);
																} else {
																	ghost.setMovement(3);
																}
															} else if (tabrange[0] >= 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(1);
																}
															} else if (tabrange[2] > 0) {
																int a = (int) Math.floor(Math.random() * 2);
																if (a == 0) {
																	ghost.setMovement(3);
																}
															}
														} else if (levelConstruction.get(ghost.getYPosition(), ghost.getXPosition() - 1) == 1) {
															ghost.setMovement(2);
														}
													}

												}
												
												for (Ghost ghost : arrayghost2) {
													if (deftext.textProperty().get().equals("The game has started, good luck!")) {
															ghost.move();
													}
												}
											}			

											for (Ghost ghost : arrayghost2) {
												if (pacman2.getXPosition() == ghost.getXPosition() && pacman2.getYPosition() == ghost.getYPosition()) {
													animation.stop();
													Timeline animation2 = new Timeline();
													animation2.getKeyFrames().addAll(
															new KeyFrame(new Duration(0.0), new KeyValue(pacpaneConstruction.opacityProperty(), 1.0)),
															new KeyFrame(new Duration(2000.0),
																	new KeyValue(pacpaneConstruction.opacityProperty(), 0.0, Interpolator.LINEAR)));
													animation2.play();
												}
											}
											pacman2.addTime();
										}
									}));
									animation.play();
								}
							});
						}
					}
				});
			}
		});

		main.getChildren().addAll(grid, pacpane, explications);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
