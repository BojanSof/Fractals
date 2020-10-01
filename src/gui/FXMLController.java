package gui;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fractals.Board;
import fractals.CantorSet;
import fractals.Crystal;
import fractals.Fractal;
import fractals.HTree;
import fractals.HilbertCurve;
import fractals.KochSnowflake;
import fractals.MandelbrotSet;
import fractals.PeanoCurve;
import fractals.SierpinskiCarpet;
import fractals.SierpinskiTriangle;
import fractals.Tree;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import utils.ColorMap;

public class FXMLController {
	@FXML private BorderPane mainPane;
	@FXML private VBox vboxOptions;
	@FXML private Label lblFractals;
	@FXML private ListView<String> lvFractals;
	@FXML private Label lblImageOptions;
	@FXML private GridPane gridImageOptions;
	@FXML private Label lblImageWidth;
	@FXML private TextField tfImageWidth;
	@FXML private Label lblImageHeight;
	@FXML private TextField tfImageHeight;
	@FXML private Label lblBackgroung;
	@FXML private ComboBox<String> comboBackground;
	@FXML private Button btnSaveImage;
	@FXML private Label lblOptions;
	@FXML private HBox hboxOrder;
	@FXML private Label lblOrder;
	@FXML private TextField tfOrder;
	@FXML private Button btnIncreaseOrder;
	@FXML private Button btnDecreaseOrder;
	@FXML private Separator separator1;
	@FXML private CheckBox cbFilled;
	@FXML private HBox hboxAngle;
	@FXML private Label lblAngle;
	@FXML private Slider sliderAngle;
	@FXML private TextField tfAngle;
	@FXML private Separator separator2;
	@FXML private GridPane gridMandelbrot;
	@FXML private Label lblIterations;
	@FXML private TextField tfIterations;
	@FXML private Label lblRadius;
	@FXML private TextField tfRadius;
	@FXML private Label lblRealMin;
	@FXML private TextField tfRealMin;
	@FXML private Label lblRealMax;
	@FXML private TextField tfRealMax;
	@FXML private Label lblImagMin;
	@FXML private TextField tfImagMin;
	@FXML private Label lblImagMax;
	@FXML private TextField tfImagMax;
	@FXML private Label lblColorMap;
	@FXML private ComboBox<String> comboColorMap;
	@FXML private Button btnMandelbrotDraw;
	@FXML private Canvas canvas;
	@FXML private StackPane paneCanvas;
	
	private Fractal fractal;
	
	@FXML
	public void initialize() {
		
		vboxOptions.getChildren().remove(cbFilled);
		vboxOptions.getChildren().remove(separator2);
		vboxOptions.getChildren().remove(hboxAngle);
		vboxOptions.getChildren().remove(gridMandelbrot);
		
		this.canvas.widthProperty().addListener(ov->tfImageWidth.setText(Integer.toString((int)this.canvas.getWidth())));
		this.canvas.heightProperty().addListener(ov->tfImageHeight.setText(Integer.toString((int)this.canvas.getHeight())));
		comboBackground.getItems().addAll("Transparent", "White");
		comboBackground.setValue("Transparent");
		lvFractals.getItems().addAll(FXCollections.observableArrayList(Fractal.getFractalsList()));
		lvFractals.getSelectionModel().selectedItemProperty().addListener(ov-> {
			vboxOptions.getChildren().remove(cbFilled);
			vboxOptions.getChildren().remove(separator2);
			vboxOptions.getChildren().remove(hboxAngle);
			vboxOptions.getChildren().remove(gridMandelbrot);
			if(!vboxOptions.getChildren().contains(hboxOrder)) {
				vboxOptions.getChildren().add(hboxOrder);
				vboxOptions.getChildren().add(separator1);
			}
			tfOrder.setText("0");
			
			if(this.canvas.getOnMousePressed() != null)
				this.canvas.setOnMousePressed(null);
			if(this.canvas.getOnMouseDragged() != null)
				this.canvas.setOnMouseDragged(null);
			if(this.canvas.getOnMouseReleased() != null)
				this.canvas.setOnMouseReleased(null);
			
			switch(lvFractals.getSelectionModel().getSelectedItem()) {
				case "Board":
					fractal = new Board(canvas);
				break;
				case "Cantor Set":
					fractal = new CantorSet(canvas);
				break;
				case "Crystal":
					fractal = new Crystal(canvas);
				break;
				case "Hilbert Curve":
					fractal = new HilbertCurve(canvas);
				break;
				case "HTree":
					fractal = new HTree(canvas);
				break;
				case "Koch Snowflake":
					fractal = new KochSnowflake(canvas);
				break;
				case "Mandelbrot Set": {
					fractal = new MandelbrotSet(canvas);
					vboxOptions.getChildren().remove(hboxOrder);
					vboxOptions.getChildren().remove(separator1);
					vboxOptions.getChildren().add(gridMandelbrot);
					vboxOptions.getChildren().add(separator2);
					comboColorMap.setValue(ColorMap.getColorMapsNames()[ColorMap.indexOfColorMap(((MandelbrotSet)fractal).getColorMap())]);
					((MandelbrotSet) fractal).getChangeProperty().addListener((observable, oldValue, newValue) -> {
						if (newValue == true) {
							tfIterations.setText(Integer.toString(((MandelbrotSet) fractal).getIterations()));
							tfRadius.setText(Double.toString(((MandelbrotSet) fractal).getEscapeRadius()));
							tfRealMin.setText(Double.toString(((MandelbrotSet) fractal).getRealMin()));
							tfRealMax.setText(Double.toString(((MandelbrotSet) fractal).getRealMax()));
							tfImagMin.setText(Double.toString(((MandelbrotSet) fractal).getImagMin()));
							tfImagMax.setText(Double.toString(((MandelbrotSet) fractal).getImagMax()));
							comboColorMap.setValue(ColorMap.getColorMapsNames()[ColorMap
									.indexOfColorMap(((MandelbrotSet) fractal).getColorMap())]);
							((MandelbrotSet) fractal).getChangeProperty().set(false);
						}
					});
					fractal.draw();
				} break;
				case "Peano Curve":
					fractal = new PeanoCurve(canvas);
				break;
				case "Sierpinski Carpet":
					fractal = new SierpinskiCarpet(canvas);
				break;
				case "Sierpinski Triangle": 
					fractal = new SierpinskiTriangle(canvas);
					vboxOptions.getChildren().addAll(cbFilled, separator2);
					cbFilled.setSelected(((SierpinskiTriangle)fractal).getFilled());
				break;
				case "Tree":
					fractal = new Tree(canvas);
					sliderAngle.setValue(((Tree)fractal).getAngle());
					tfAngle.setText(Double.toString(((int)(((Tree)fractal).getAngle() * 10) / 10.0)));
					vboxOptions.getChildren().addAll(hboxAngle, separator2);
				break;
				default: break;
			}
		});
		
		sliderAngle.valueProperty().addListener(ov-> {
			if(fractal instanceof Tree) {
				((Tree)fractal).setAngle(sliderAngle.getValue());
				tfAngle.setText(Double.toString(((int)(((Tree)fractal).getAngle()* 10) / 10.0)));
			}
		});
		sliderAngle.setBlockIncrement(1.0f);
		
		comboColorMap.getItems().addAll(FXCollections.observableArrayList(ColorMap.getColorMapsNames()));
		comboColorMap.getSelectionModel().selectedItemProperty().addListener(ov-> {
			if(fractal instanceof MandelbrotSet) {
				((MandelbrotSet)fractal).setColorMap(ColorMap.getColorMaps()[comboColorMap.getSelectionModel().getSelectedIndex()]);
			}
		});
		
		paneCanvas.widthProperty().addListener(ov-> {
			canvas.setWidth(paneCanvas.getWidth());
			fractal.draw();
		});
		paneCanvas.heightProperty().addListener(ov-> {
			canvas.setHeight(paneCanvas.getHeight());
			fractal.draw();
		});
		
		fractal = new Fractal(canvas);
	}
	
	@FXML
	private void btnSaveImageAction(ActionEvent event) {
		int width, height;
		try {
			width = Integer.parseInt(tfImageWidth.getText());
			height = Integer.parseInt(tfImageHeight.getText());
		} catch(NumberFormatException ex) {
			width = 1920;
			tfImageWidth.setText(Integer.toString(width));
			height = 1080;
			tfImageHeight.setText(Integer.toString(height));
		}
		Canvas imageCanvas = new Canvas(width, height);
		@SuppressWarnings("unused") Fractal imageFractal = fractal.copy(imageCanvas);
		FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
        											new FileChooser.ExtensionFilter("PNG (*.png)", "*.png")
        										);
        File file = fileChooser.showSaveDialog(this.canvas.getScene().getWindow());
        if(file != null){
            try {
                WritableImage writableImage = new WritableImage((int)imageCanvas.getWidth(), (int)imageCanvas.getHeight());
                SnapshotParameters snapshotParameters = new SnapshotParameters();
                switch(comboBackground.getSelectionModel().getSelectedItem()) {
                	case "Transparent":
                		snapshotParameters.setFill(Color.TRANSPARENT);
                	break;
                	case "White":
                		snapshotParameters.setFill(Color.WHITE);
                	break;
                	default:
                		snapshotParameters.setFill(Color.TRANSPARENT);
                	break;
                }
                imageCanvas.snapshot(snapshotParameters, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                if(ImageIO.write(renderedImage, file.getName().substring(file.getName().lastIndexOf(".") + 1), file)) {
                	Alert alert = new Alert(AlertType.CONFIRMATION, "Image " + file.getName() + " created successfully", ButtonType.CLOSE);
                	alert.show();
                } else {
                	Alert alert = new Alert(AlertType.ERROR, "Can't create image " + file.getName(), ButtonType.CLOSE);
                    alert.show();
                }
            } catch (IOException ex) {
                Alert alert = new Alert(AlertType.ERROR, "Can't create image " + file.getName(), ButtonType.CLOSE);
                alert.show();
            }
        }
	}
	
	@FXML
	private void tfOrderAction(ActionEvent event) {
		try {
			fractal.setOrder(Integer.parseInt(tfOrder.getText()));
		} catch(NumberFormatException ex) {
			fractal.setOrder(0);
			tfOrder.setText("0");
		}
	}
	
	@FXML
	private void btnIncreaseOrderAction(ActionEvent event) {
		fractal.setOrder(fractal.getOrder() + 1);
		tfOrder.setText(Integer.toString(fractal.getOrder()));
	}
	
	@FXML
	private void btnDecreaseOrderAction(ActionEvent event) {
		fractal.setOrder(fractal.getOrder() - 1);
		tfOrder.setText(Integer.toString(fractal.getOrder()));
	}
	
	@FXML
	private void cbFilledAction(ActionEvent event) {
		if(fractal instanceof SierpinskiTriangle) {
			if(cbFilled.isSelected()) {
				((SierpinskiTriangle)fractal).setFilled(true);
			} else {
				((SierpinskiTriangle)fractal).setFilled(false);
			}
		}
	}
	
	@FXML
	private void tfAngleAction(ActionEvent event) {
		try {
			if(fractal instanceof Tree) {
				double angle = Double.parseDouble(tfAngle.getText());
				if(angle < 0.0 || angle > 180.0) angle = ((Tree)fractal).getAngle();
				((Tree)fractal).setAngle(angle);
			}
		} catch(NumberFormatException ex) {
			
		} finally {
			tfAngle.setText(Double.toString(((Tree)fractal).getAngle()));
			sliderAngle.setValue(((Tree)fractal).getAngle());
		}
	}
	
	@FXML
	private void tfRadiusAction(ActionEvent event) {
		try {
			if(fractal instanceof MandelbrotSet) {
				double radius = Double.parseDouble(tfRadius.getText());
				((MandelbrotSet)fractal).setEscapeRadius(radius);
			}
		} catch(NumberFormatException ex) {
			
		} finally {
			tfRadius.setText(Double.toString(((MandelbrotSet)fractal).getEscapeRadius()));
		}
	}
	
	@FXML
	private void tfIterationsAction(ActionEvent event) {
		try {
			if(fractal instanceof MandelbrotSet) {
				int iterations = Integer.parseInt(tfIterations.getText());
				((MandelbrotSet)fractal).setIterations(iterations);
			}
		} catch(NumberFormatException ex) {
			
		} finally {
			tfIterations.setText(Integer.toString(((MandelbrotSet)fractal).getIterations()));
		}
	}
	
	@FXML
	private void tfRealMinAction(ActionEvent event) {
		try {
			if(fractal instanceof MandelbrotSet) {
				double realMin = Double.parseDouble(tfRealMin.getText());
				((MandelbrotSet)fractal).setRealMin(realMin);
			}
		} catch(NumberFormatException ex) {
			
		} finally {
			tfRealMin.setText(Double.toString(((MandelbrotSet)fractal).getRealMin()));
		}
	}
	
	@FXML
	private void tfRealMaxAction(ActionEvent event) {
		try {
			if(fractal instanceof MandelbrotSet) {
				double realMax = Double.parseDouble(tfRealMax.getText());
				((MandelbrotSet)fractal).setRealMax(realMax);
			}
		} catch(NumberFormatException ex) {
			
		} finally {
			tfRealMax.setText(Double.toString(((MandelbrotSet)fractal).getRealMax()));
		}
	}
	
	@FXML
	private void tfImagMinAction(ActionEvent event) {
		try {
			if(fractal instanceof MandelbrotSet) {
				double imagMin = Double.parseDouble(tfImagMin.getText());
				((MandelbrotSet)fractal).setImagMin(imagMin);
			}
		} catch(NumberFormatException ex) {
			
		} finally {
			tfImagMin.setText(Double.toString(((MandelbrotSet)fractal).getImagMin()));
		}
	}
	
	@FXML
	private void tfImagMaxAction(ActionEvent event) {
		try {
			if(fractal instanceof MandelbrotSet) {
				double imagMax = Double.parseDouble(tfImagMax.getText());
				((MandelbrotSet)fractal).setImagMax(imagMax);
			}
		} catch(NumberFormatException ex) {
			
		} finally {
			tfImagMax.setText(Double.toString(((MandelbrotSet)fractal).getImagMax()));
		}
	}
	
	@FXML
	private void btnMandelbrotDrawAction(ActionEvent event) {
		if(fractal instanceof MandelbrotSet) {
			try {
				int iterations = Integer.parseInt(tfIterations.getText());
				((MandelbrotSet)fractal).setIterations(iterations);
				double radius = Double.parseDouble(tfRadius.getText());
				((MandelbrotSet)fractal).setEscapeRadius(radius);
				double realMin = Double.parseDouble(tfRealMin.getText());
				((MandelbrotSet)fractal).setRealMin(realMin);
				double realMax = Double.parseDouble(tfRealMax.getText());
				((MandelbrotSet)fractal).setRealMax(realMax);
				double imagMax = Double.parseDouble(tfImagMax.getText());
				((MandelbrotSet)fractal).setImagMax(imagMax);
				double imagMin = Double.parseDouble(tfImagMin.getText());
				((MandelbrotSet)fractal).setImagMin(imagMin);
			} catch(NumberFormatException ex) {
				
			} finally {
				tfIterations.setText(Integer.toString(((MandelbrotSet)fractal).getIterations()));
				tfRadius.setText(Double.toString(((MandelbrotSet)fractal).getEscapeRadius()));
				tfRealMin.setText(Double.toString(((MandelbrotSet)fractal).getRealMin()));
				tfRealMax.setText(Double.toString(((MandelbrotSet)fractal).getRealMax()));
				tfImagMin.setText(Double.toString(((MandelbrotSet)fractal).getImagMin()));
				tfImagMax.setText(Double.toString(((MandelbrotSet)fractal).getImagMax()));
			}
			fractal.draw();
		}
	}
}