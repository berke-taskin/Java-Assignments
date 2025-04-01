import java.io.IOException;
import java.util.Arrays;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.*;

public class PlottingGraphs {
    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis, String a, String b, String c) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries(a, doubleX, yAxis[0]);
        chart.addSeries(b, doubleX, yAxis[1]);
        chart.addSeries(c, doubleX, yAxis[2]);

        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper<XYChart>(chart).displayChart();
    }
}
