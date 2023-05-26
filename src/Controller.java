import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Controller {

    String nama = "", nim = "", namaInput, nimInput;
    String charNama = "@.+/- aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
    String charNim = "0123456789";
    String scan;

    @FXML
    private TextField labelNama, labelNama2, labelNim, labelNim2, scanNamaField, scanNimField;

    @FXML
    private Label commentLabel, commentLabel2, commentLabel3, commentLabel4;

    @FXML
    void clickScan(ActionEvent event) {
        namaInput = labelNama.getText();
        nimInput = labelNim.getText();
        Thread queryThread = new Thread() {
            public void run() {
                scanNama(event);
                scanNim(event);
                Platform.runLater(() -> {commentLabel.setText(null);});
                scanNamaField.setText(null);
                scanNimField.setText(null);
            }
        };
        queryThread.start();
    }

    @FXML
    void clickClear (ActionEvent event){
        nama = "";
        nim = "";
        Platform.runLater(() -> {commentLabel.setText(null);});
        scanNamaField.setText(null);
        scanNimField.setText(null);
        labelNama.setText(null);
        labelNama.setPromptText("Nama");
        labelNim.setText(null);
        labelNim.setPromptText("NIM");
        labelNama2.setText(null);
        labelNim2.setText(null);
        commentLabel3.setText(null);
        commentLabel4.setText(null);
    }

    private void scanNama(ActionEvent evtAct) {
        try {
            if (!nama.equals(namaInput)) {
                for (int j = 0; j < namaInput.length(); j++) {
                    for (int i = 0; i < charNama.length(); i++) {
                        scanNamaField.setText(Character.toString(charNama.charAt(i)));
                        Thread.sleep(200);
                        if (charNama.charAt(i) == namaInput.charAt(j)) {
                            scan = Character.toString(charNama.charAt(i));
                            if (nama.length() == 0)
                                nama = scan;
                            else
                                nama += scan;

                            Platform.runLater(() -> {
                                commentLabel2.setTextFill(Color.GREEN);
                                commentLabel2.setText("Karakter Diterima");
                            });
                            labelNama2.setText(nama);
                            Thread.sleep(800);
                            Platform.runLater(() -> {commentLabel2.setText(null);});

                            if (nama.equals(namaInput)){
                                Platform.runLater(() -> {commentLabel3.setText("Scan Nama Berhasil!");});
                                Thread.sleep(400);
                            }
                            break;

                        }else {
                            Platform.runLater(() -> {
                                commentLabel2.setTextFill(Color.RED);
                                commentLabel2.setText("Karakter Ditolak");}
                            );
                            Thread.sleep(600);
                            Platform.runLater(() -> {commentLabel2.setText(null);});
                        }
                    }
                }
            }
            Thread.sleep(600);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void scanNim(ActionEvent evtAct) {
        try {
            if (!nim.equals(nimInput)) {
                for (int j = 0; j < nimInput.length(); j++) {
                    for (int i = 0; i < charNim.length(); i++) {
                        scanNimField.setText(Character.toString(charNim.charAt(i)));
                        Thread.sleep(200);
                        if (charNim.charAt(i) == nimInput.charAt(j)) {
                            scan = Character.toString(charNim.charAt(i));
                            if (nim.length() == 0)
                                nim = scan;
                            else
                                nim += scan;

                            Platform.runLater(() -> {
                                commentLabel.setTextFill(Color.GREEN);
                                commentLabel.setText("Karakter Diterima");
                            });
                            labelNim2.setText(nim);
                            Thread.sleep(800);
                            Platform.runLater(() -> {commentLabel.setText(null);});

                            if (nim.equals(nimInput)){
                                Platform.runLater(() -> {commentLabel4.setText("Scan NIM Berhasil!");});
                                Thread.sleep(400);
                            }
                            break;

                        } else {
                            Platform.runLater(() -> {
                                commentLabel.setTextFill(Color.RED);
                                commentLabel.setText("Karakter Ditolak");}
                            );
                            Thread.sleep(600);
                            Platform.runLater(() -> {commentLabel.setText(null);});
                        }
                    }
                }
            }
            Thread.sleep(600);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
// convert delay value ; 500 -> 200 ; 1000 -> 400 ; 1900 -> 750