package SineGraphFinalProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class SineGraphFinalProjectController {

    @FXML
    private TextField RightBoundInputArea;
    @FXML
    private TextField numberOfTermsArea;
    @FXML
    private CheckBox appendGraphButton;
    @FXML
    private Button runSimulationButton;
    @FXML
    private TextField textSummary;
    @FXML
    private LineChart<?, ?> graphArea;
    @FXML
    private TextField LeftBoundInputArea;

    StringBuilder sb = new StringBuilder();

    private int factorial(int number) {
        if (number == 1) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }

    private double estimateSine(int currentTerm, int maximumNumberOfTerms, double angleInDegrees) {
        double angleInRadians = angleInDegrees * Math.PI / 180;
        int temp = (2 * currentTerm + 1);
        double nume = Math.pow(-1, currentTerm);
        double denom = factorial(temp);
        double currentTermValue = (nume / denom) * Math.pow(angleInRadians, temp);

        if (currentTerm == maximumNumberOfTerms - 1) {
            if (currentTermValue < 0) {
                sb.append(" - ").append(Math.abs(currentTermValue)).append("\n");

            } else if (currentTerm != 0) {
                sb.append(" + ").append(currentTermValue).append("\n");
            } else {
                sb.append(Math.abs(currentTermValue)).append("\n");
            }
            return currentTermValue;

        } else {
            if (currentTermValue < 0) {
                sb.append(" - ").append(Math.abs(currentTermValue)).append("\n");
            } else if (currentTerm != 0 && currentTerm != maximumNumberOfTerms && currentTermValue > 0) {
                sb.append(" + ").append(currentTermValue).append("\n");
            } else {
                sb.append(currentTermValue).append("\n");
            }
            return currentTermValue + estimateSine(currentTerm + 1, maximumNumberOfTerms, angleInDegrees);
        }

    }

    @FXML
    private void updateGraph(ActionEvent event) {
        if (appendGraphButton.isSelected()) {
            createSineGraph();
            createTaylorGraph();
        } else {
            graphArea.getData().clear();
            createSineGraph();
            createTaylorGraph();
        }
    }

    private void createSineGraph() {
        int rightBound = Integer.parseInt(RightBoundInputArea.getText());
        int leftBound = Integer.parseInt(LeftBoundInputArea.getText());
        XYChart.Series sineGraph = new XYChart.Series();
        sineGraph.setName("Sine Graph");
        for (int currentAngle = leftBound; currentAngle < rightBound; currentAngle++) {

            sineGraph.getData().add(new XYChart.Data((currentAngle), Math.sin(currentAngle * Math.PI / 180)));
        }
        graphArea.getData().add(sineGraph);
        graphArea.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
    }

    private void createTaylorGraph() {
        textSummary.clear();
        double rightBound = Integer.parseInt(RightBoundInputArea.getText());
        double leftBound = Integer.parseInt(LeftBoundInputArea.getText());
        int numberOfTerms = Integer.parseInt(numberOfTermsArea.getText());
        int currentTerm = 0;
        XYChart.Series taylorGraph = new XYChart.Series();
        taylorGraph.setName("Sine Graph");
        for (double currentAngle = leftBound; currentAngle <= rightBound; currentAngle++) {
            sb.setLength(0);
            sb.append("\n");
            sb.append("Sine(").append(currentAngle).append(") = ");
            taylorGraph.getData().add(new XYChart.Data((currentAngle), estimateSine(currentTerm, numberOfTerms, currentAngle)));
            textSummary.appendText(sb.toString()+"\n");
        }
        
        graphArea.getData().add(taylorGraph);
        graphArea.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);

    }

}
