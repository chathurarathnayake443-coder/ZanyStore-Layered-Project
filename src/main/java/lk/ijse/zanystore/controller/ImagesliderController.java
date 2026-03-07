////package lk.ijse.zanystore.controller;
////
////import javafx.animation.KeyFrame;
////import javafx.animation.PauseTransition;
////import javafx.animation.Timeline;
////import javafx.animation.TranslateTransition;
////import javafx.fxml.FXML;
////import javafx.fxml.Initializable;
////import javafx.scene.Node;
////import javafx.scene.image.Image;
////import javafx.scene.image.ImageView;
////import javafx.scene.layout.AnchorPane;
////import javafx.scene.layout.HBox;
////import javafx.scene.paint.Color;
////import javafx.scene.shape.Circle;
////import javafx.scene.shape.Rectangle;
////import javafx.util.Duration;
////
////import java.io.InputStream;
////import java.net.URL;
////import java.util.ArrayList;
////import java.util.List;
////import java.util.ResourceBundle;
////
////public class ImagesliderController implements Initializable {
////
////    @FXML
////    private AnchorPane clipPane;          // container defined in FXML
////
////    @FXML
////    private HBox dotsContainer;           // dots HBox
////
////    // settings
////    private final double WIDTH = 1000;     // visible width (match FXML or adjust)
////    private final double HEIGHT = 900;    // visible height (match FXML or adjust)
////    private final Duration SLIDE_DURATION = Duration.millis(600); // animation duration
////    private final Duration PAUSE_BETWEEN = Duration.seconds(2.0); // time each slide stays
////
////    // classpath resources (update names if necessary)
////    private final List<String> imagePaths = List.of(
////            "/lk/ijse/zanystore/images/pexels-kseniachernaya-3965545.jpg",
////            "/lk/ijse/zanystore/images/wallpaperflare.com_wallpaper.jpg",
////            "/lk/ijse/zanystore/images/pexels-atelierbyvineeth-35045844.jpg"
////    );
////
////    private final List<ImageView> imageViews = new ArrayList<>();
////    private HBox imagesBox;                // holds imageviews horizontally
////    private int currentIndex = 0;
////
////    private Timeline autoAdvanceTimeline;  // drives auto slide
////
////    @Override
////    public void initialize(URL location, ResourceBundle resources) {
////        prepareContainer();
////        loadImages();
////        buildDots();
////        startAutoAdvance();
////        setupHoverPause();
////        
////        clipPane.toBack();
////        imagesBox.setMouseTransparent(true);
////        
////        
////    }
////
////    private void prepareContainer() {
////        // create the HBox where images will be placed
////        imagesBox = new HBox();
////        imagesBox.setSpacing(0);
////
////        // create a clip rectangle (so images outside the visible area are not shown)
////        Rectangle clipRect = new Rectangle(WIDTH, HEIGHT);
////        clipPane.setClip(clipRect);
////
////        // set size for clipPane and add the imagesBox to it
////        clipPane.setPrefWidth(WIDTH);
////        clipPane.setPrefHeight(HEIGHT);
////
////        AnchorPane.setLeftAnchor(imagesBox, 0.0);
////        AnchorPane.setTopAnchor(imagesBox, 0.0);
////
////        clipPane.getChildren().add(imagesBox);
////    }
////
////    private void loadImages() {
////        for (String path : imagePaths) {
////            try (InputStream is = getClass().getResourceAsStream(path)) {
////                if (is == null) {
////                    System.err.println("Image resource not found: " + path);
////                    ImageView placeholder = createPlaceholderView();
////                    imageViews.add(placeholder);
////                    imagesBox.getChildren().add(placeholder);
////                    continue;
////                }
////                // CORRECTED: use Image(InputStream) then set ImageView sizes
////                Image img = new Image(is);
////                ImageView iv = new ImageView(img);
////                iv.setFitWidth(WIDTH);
////                iv.setFitHeight(HEIGHT);
////                iv.setPreserveRatio(false);
////                imageViews.add(iv);
////                imagesBox.getChildren().add(iv);
////            } catch (Exception ex) {
////                ex.printStackTrace();
////                ImageView placeholder = createPlaceholderView();
////                imageViews.add(placeholder);
////                imagesBox.getChildren().add(placeholder);
////            }
////        }
////    }
////
////    private ImageView createPlaceholderView() {
////        ImageView iv = new ImageView();
////        iv.setFitWidth(WIDTH);
////        iv.setFitHeight(HEIGHT);
////        iv.setStyle("-fx-background-color: #dddddd;");
////        return iv;
////    }
////
////    private void buildDots() {
////        dotsContainer.getChildren().clear();
////        for (int i = 0; i < imageViews.size(); i++) {
////            Circle dot = new Circle(6);
////            dot.setFill(i == currentIndex ? Color.web("#ff2d2d") : Color.web("#BBBBBB"));
////            final int idx = i;
////            dot.setOnMouseClicked(e -> jumpTo(idx));
////            dotsContainer.getChildren().add(dot);
////        }
////    }
////
////    private void updateDots() {
////        for (int i = 0; i < dotsContainer.getChildren().size(); i++) {
////            Node n = dotsContainer.getChildren().get(i);
////            if (n instanceof Circle) {
////                ((Circle) n).setFill(i == currentIndex ? Color.web("#ff2d2d") : Color.web("#BBBBBB"));
////            }
////        }
////    }
////
////    private void startAutoAdvance() {
////        if (imageViews.isEmpty()) return;
////
////        autoAdvanceTimeline = new Timeline(
////                new KeyFrame(PAUSE_BETWEEN, e -> advanceOne())
////        );
////        autoAdvanceTimeline.setCycleCount(Timeline.INDEFINITE);
////        autoAdvanceTimeline.play();
////    }
////
////    private void advanceOne() {
////        int next = (currentIndex + 1) % imageViews.size();
////        animateTo(next);
////    }
////
////    private void jumpTo(int index) {
////        if (index < 0 || index >= imageViews.size() || index == currentIndex) return;
////        animateTo(index);
////    }
////
////    private void animateTo(int index) {
////        if (autoAdvanceTimeline != null) autoAdvanceTimeline.pause();
////
////        double fromX = -currentIndex * WIDTH;
////        double toX = -index * WIDTH;
////
////        TranslateTransition tt = new TranslateTransition(SLIDE_DURATION, imagesBox);
////        tt.setFromX(fromX);
////        tt.setToX(toX);
////        tt.setOnFinished(e -> {
////            currentIndex = index;
////            updateDots();
////            PauseTransition p = new PauseTransition(PAUSE_BETWEEN);
////            p.setOnFinished(ev -> {
////                if (autoAdvanceTimeline != null) autoAdvanceTimeline.play();
////            });
////            p.play();
////        });
////        tt.play();
////    }
////
////    private void setupHoverPause() {
////        clipPane.setOnMouseEntered(e -> {
////            if (autoAdvanceTimeline != null) autoAdvanceTimeline.pause();
////        });
////        clipPane.setOnMouseExited(e -> {
////            if (autoAdvanceTimeline != null) autoAdvanceTimeline.play();
////        });
////    }
////
////    /**
////     * Stop the timeline if you need to when closing the window.
////     */
////    public void stop() {
////        if (autoAdvanceTimeline != null) autoAdvanceTimeline.stop();
////    }
////}
//
////package lk.ijse.zanystore.controller;
////
////import javafx.animation.KeyFrame;
////import javafx.animation.PauseTransition;
////import javafx.animation.Timeline;
////import javafx.animation.TranslateTransition;
////import javafx.application.Platform;
////import javafx.fxml.FXML;
////import javafx.fxml.Initializable;
////import javafx.scene.Cursor;
////import javafx.scene.image.Image;
////import javafx.scene.image.ImageView;
////import javafx.scene.input.MouseEvent;
////import javafx.scene.layout.AnchorPane;
////import javafx.scene.layout.HBox;
////import javafx.scene.shape.Rectangle;
////import javafx.util.Duration;
////
////import java.io.InputStream;
////import java.net.URL;
////import java.util.ArrayList;
////import java.util.List;
////import java.util.ResourceBundle;
////
/////**
//// * Robust image slider controller — defensive about z-order / mouse events.
//// *
//// * Requirements:
//// * - Place images in: src/main/resources/lk/ijse/zanystore/images/
//// * - Filenames in imagePaths must match.
//// * - Use the FXML you already have where fx:id "clipPane" and "dotsContainer" exist.
//// */
////public class ImagesliderController implements Initializable {
////
////    @FXML
////    private AnchorPane clipPane;          // visible viewport (defined in FXML)
////
////    @FXML
////    private HBox dotsContainer;           // (optional) you can ignore if not used
////
////    // slider settings (tweak as needed)
////    private final double WIDTH = 850;
////    private final double HEIGHT = 660;
////    private final Duration SLIDE_DURATION = Duration.millis(600);
////    private final Duration PAUSE_BETWEEN = Duration.seconds(2.0);
////
////    // classpath images (update filenames to match yours)
////    private final List<String> imagePaths = List.of(
////            "/lk/ijse/zanystore/images/pexels-kseniachernaya-3965545.jpg",
////            "/lk/ijse/zanystore/images/wallpaperflare.com_wallpaper.jpg",
////            "/lk/ijse/zanystore/images/pexels-atelierbyvineeth-35045844.jpg"
////    );
////
////    private final List<ImageView> imageViews = new ArrayList<>();
////    private HBox imagesBox;                // horizontal container for ImageViews
////    private int currentIndex = 0;
////    private Timeline autoAdvanceTimeline;
////
////    @Override
////    public void initialize(URL location, ResourceBundle resources) {
////        prepareContainer();
////        loadImages();
////        // optional dots: buildDots(); // you can leave out if you removed dots in FXML
////
////        // IMPORTANT: Do the z-order and mouse-transparency adjustments later on the FX thread
////        // after the scene graph is built so they reliably take effect.
////        Platform.runLater(() -> {
////            // 1) Place slider behind other siblings (so login controls on top)
////            try {
////                clipPane.toBack();
////            } catch (Exception ignored) {}
////
////            // 2) Make images not intercept mouse events so underlying controls remain clickable
////            if (imagesBox != null) {
////                imagesBox.setMouseTransparent(true);
////                // also set each image view transparent to mouse to be extra safe
////                for (ImageView iv : imageViews) iv.setMouseTransparent(true);
////            }
////
////            // 3) Ensure clipPane does not block clicks where it has no visible children
////            clipPane.setPickOnBounds(false);
////        });
////
////        startAutoAdvance();
////        setupHoverPause(); // clipPane will still receive hover events (mouseTransparent on imagesBox doesn't disable that)
////        
////        clipPane.setMouseTransparent(true);   // important
////imagesBox.setMouseTransparent(true);  // important
////    }
////
////    private void prepareContainer() {
////        imagesBox = new HBox();
////        imagesBox.setSpacing(0);
////
////        // clip rectangle so only the viewport area shows
////        Rectangle clipRect = new Rectangle(WIDTH, HEIGHT);
////        clipPane.setClip(clipRect);
////
////        clipPane.setPrefWidth(WIDTH);
////        clipPane.setPrefHeight(HEIGHT);
////
////        AnchorPane.setLeftAnchor(imagesBox, 0.0);
////        AnchorPane.setTopAnchor(imagesBox, 0.0);
////
////        clipPane.getChildren().add(imagesBox);
////    }
////
////    private void loadImages() {
////        imageViews.clear();
////        imagesBox.getChildren().clear();
////        for (String path : imagePaths) {
////            try (InputStream is = getClass().getResourceAsStream(path)) {
////                if (is == null) {
////                    System.err.println("Image resource not found: " + path);
////                    ImageView placeholder = createPlaceholderView();
////                    imageViews.add(placeholder);
////                    imagesBox.getChildren().add(placeholder);
////                    continue;
////                }
////                Image img = new Image(is);
////                ImageView iv = new ImageView(img);
////                iv.setFitWidth(WIDTH);
////                iv.setFitHeight(HEIGHT);
////                iv.setPreserveRatio(false); // fill the viewport — change to true if you want letterboxing
////                iv.setSmooth(true);
////                iv.setCache(true);
////
////                // ensure imageViews won't accept mouse events (so underlying controls stay interactive)
////                iv.setMouseTransparent(true);
////
////                imageViews.add(iv);
////                imagesBox.getChildren().add(iv);
////            } catch (Exception ex) {
////                ex.printStackTrace();
////                ImageView placeholder = createPlaceholderView();
////                imageViews.add(placeholder);
////                imagesBox.getChildren().add(placeholder);
////            }
////        }
////    }
////
////    private ImageView createPlaceholderView() {
////        ImageView iv = new ImageView();
////        iv.setFitWidth(WIDTH);
////        iv.setFitHeight(HEIGHT);
////        iv.setStyle("-fx-background-color: #dddddd;");
////        iv.setMouseTransparent(true);
////        return iv;
////    }
////
////    private void startAutoAdvance() {
////        if (imageViews.isEmpty()) return;
////
////        // initial position (ensure first image visible)
////        imagesBox.setTranslateX(0);
////
////        autoAdvanceTimeline = new Timeline(new KeyFrame(PAUSE_BETWEEN, e -> advanceOne()));
////        autoAdvanceTimeline.setCycleCount(Timeline.INDEFINITE);
////        autoAdvanceTimeline.play();
////    }
////
////    private void advanceOne() {
////        int next = (currentIndex + 1) % imageViews.size();
////        animateTo(next);
////    }
////
////    private void animateTo(int index) {
////        if (index < 0 || index >= imageViews.size()) return;
////
////        if (autoAdvanceTimeline != null) autoAdvanceTimeline.pause();
////
////        double toX = -index * WIDTH;
////        TranslateTransition tt = new TranslateTransition(SLIDE_DURATION, imagesBox);
////        tt.setToX(toX);
////        tt.setOnFinished(e -> {
////            currentIndex = index;
////            // after a pause, resume auto-advance
////            PauseTransition p = new PauseTransition(PAUSE_BETWEEN);
////            p.setOnFinished(ev -> {
////                if (autoAdvanceTimeline != null) autoAdvanceTimeline.playFromStart();
////            });
////            p.play();
////        });
////        tt.play();
////    }
////
////    private void setupHoverPause() {
////        // even though imagesBox is mouseTransparent, clipPane still receives mouse entered/exited events
////        clipPane.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
////            if (autoAdvanceTimeline != null) autoAdvanceTimeline.pause();
////        });
////        clipPane.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
////            if (autoAdvanceTimeline != null) autoAdvanceTimeline.play();
////        });
////    }
////
////    public void stop() {
////        if (autoAdvanceTimeline != null) autoAdvanceTimeline.stop();
////    }
////}
////
//
package lk.ijse.zanystore.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ImagesliderController implements Initializable {

    @FXML
    private ImageView imageView;

    private final List<String> imagePaths = new ArrayList<>();

    private int currentIndex = 0;
    private Timeline timeline;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
        imagePaths.add("/lk/ijse/zanystore/images/WhatsApp Image 2025-12-17 at 16.49.45.jpeg");
        imagePaths.add("/lk/ijse/zanystore/images/WhatsApp Image 2025-12-17 at 16.49.42.jpeg");
        imagePaths.add("/lk/ijse/zanystore/images/WhatsApp Image 2025-12-17 at 16.49.38.jpeg");

        // debug: print resource URLs so can see if packaging is correct
        for (String p : imagePaths) {
            URL u = getClass().getResource(p);
            System.out.println("DEBUG: resource for " + p + " -> " + u);
        }

        if (!imagePaths.isEmpty()) {
            loadImageAtIndex(0);
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(3), ev -> showNextImage()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        if (imageView != null) {
            imageView.setOnMouseEntered(e -> timeline.pause());
            imageView.setOnMouseExited(e -> timeline.play());
        }
    }

    private void showNextImage() {
        if (imagePaths.isEmpty()) return;
        currentIndex = (currentIndex + 1) % imagePaths.size();
        loadImageAtIndex(currentIndex);
    }

    private void loadImageAtIndex(int idx) {
        String path = imagePaths.get(idx);

        // Try classpath resource
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is != null) {
                Image img = new Image(is);
                imageView.setImage(img);
                return;
            } else {
                System.err.println("Image resource not found on classpath: " + path);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // fallback: clear
        imageView.setImage(null);
    }

    public void setImagePaths(List<String> paths) {
        if (paths == null || paths.isEmpty()) return;
        imagePaths.clear();
        imagePaths.addAll(paths);
        currentIndex = -1;
        showNextImage();
    }

    public void stop() {
        if (timeline != null) timeline.stop();
    }
}


