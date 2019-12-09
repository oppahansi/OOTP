import com.sun.webkit.WebPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.lang.reflect.Field;

public class Controller {
    public WebView webView;
    public Button closeButton;
    public Button resetButton;
    public Button onTopButton;
    public TextField youtubeId;
    public Slider opacitySlider;

    @FXML
    public void initialize() throws NoSuchFieldException, IllegalAccessException {
        youtubeId.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue == null || newValue == null) return;
            if (!oldValue.isEmpty() && !newValue.isEmpty())
                if (oldValue.equals(newValue)) return;

            String baseUrl = "https://www.youtube.com/embed/";
            webView.getEngine().load(baseUrl + youtubeId.getText());
        });

        opacitySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            opacitySlider.getScene().getWindow().setOpacity((double)newValue / 100.0);
        });

        clearWebView();
    }

    public void onCloseButtonPressed(ActionEvent actionEvent) {
        System.exit(1);
    }

    public void onResetButtonPressed(ActionEvent actionEvent) throws NoSuchFieldException, IllegalAccessException {
        ((Stage)((Button)actionEvent.getSource()).getScene().getWindow()).setWidth(790);
        ((Stage)((Button)actionEvent.getSource()).getScene().getWindow()).setHeight(465);
        youtubeId.setText("videoId");
        clearWebView();
    }

    public void onOnTopButtonPressed(ActionEvent actionEvent) {
        if (onTopButton == null) return;
        if (onTopButton.getText().equals("Keep On Top")) onTopButton.setText("Disable On Top");
        else onTopButton.setText("Keep On Top");

        ((Stage)((Button)actionEvent.getSource()).getScene().getWindow()).setAlwaysOnTop(!onTopButton.getText().equals("Keep On Top"));
    }

    private void clearWebView() throws NoSuchFieldException, IllegalAccessException {
        webView.getEngine().loadContent("");

        Field field = webView.getEngine().getClass().getDeclaredField("page");
        field.setAccessible(true);
        WebPage page = (WebPage) field.get(webView.getEngine());
        page.setBackgroundColor((new java.awt.Color(0, 0, 0, 0)).getRGB());
    }

    public void onTextFieldEntered(MouseEvent mouseEvent) {
        Scene scene = (Scene) ((TextField)mouseEvent.getSource()).getScene();
        scene.setCursor(Cursor.TEXT);
    }

    public void onTextFieldExited(MouseEvent mouseEvent) {
        Scene scene = (Scene) ((TextField)mouseEvent.getSource()).getScene();
        scene.setCursor(Cursor.DEFAULT);
    }

    public void onWebViewEntered(MouseEvent mouseEvent) {
        Scene scene = (Scene) ((WebView)mouseEvent.getSource()).getScene();
        scene.setCursor(Cursor.HAND);
    }

    public void onWebViewExited(MouseEvent mouseEvent) {
        Scene scene = (Scene) ((WebView)mouseEvent.getSource()).getScene();
        scene.setCursor(Cursor.DEFAULT);
    }

    public void onSliderEntered(MouseEvent mouseEvent) {
        Scene scene = (Scene) ((Slider)mouseEvent.getSource()).getScene();
        scene.setCursor(Cursor.HAND);
    }

    public void onSliderExited(MouseEvent mouseEvent) {
        Scene scene = (Scene) ((Slider)mouseEvent.getSource()).getScene();
        scene.setCursor(Cursor.DEFAULT);
    }
}
