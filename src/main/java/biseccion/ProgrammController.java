package biseccion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ProgrammController {

    @FXML
    private TextField Li;
    @FXML
    private TextField Ls;
    @FXML
    private TextField Error;
    @FXML
    private TextField xField;
    @FXML
    private TextField fxField;
    @FXML
    private Button BtnCalcular;

    @FXML
    private void initialize() {
        BtnCalcular.setOnAction(event -> {
            xField.setText("Calculando...");
            fxField.setText("Calculando...");
            calcularBiseccion();
        });
    }

    private void calcularBiseccion() {
        try {
            float li = Float.parseFloat(Li.getText());
            float ls = Float.parseFloat(Ls.getText());
            float error = (float) Math.pow(10, -Float.parseFloat(Error.getText()));

            if (funcion(li) * funcion(ls) >= 0) {
                xField.setText("Error");
                fxField.setText("Intervalo inválido");
                return;
            }

            float raiz = biseccion(li, ls, error);
            xField.setText(String.format("%.6f", raiz));
            fxField.setText(String.format("%.6f", funcion(raiz)));
        } catch (NumberFormatException e) {
            xField.setText("Error");
            fxField.setText("Entrada inválida");
        }
    }

    private float biseccion(float li, float ls, float error) {
        float xr = 0, xrAnt = 0, errorCalculado;

        do {
            xr = (li + ls) / 2;
            if (xrAnt != 0) {
                errorCalculado = Math.abs((xr - xrAnt) / xrAnt);
            } else {
                errorCalculado = error + 1;
            }

            if (funcion(li) * funcion(xr) < 0) {
                ls = xr;
            } else {
                li = xr;
            }
            xrAnt = xr;
        } while (errorCalculado > error);

        return xr;
    }

    private float funcion(float x) {
        return (2 * x) - (float) Math.cos(x);
    }
}
