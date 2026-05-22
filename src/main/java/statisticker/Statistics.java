package statisticker;

import java.util.List;

public class Statistics 
{
    public enum TemperatureUnit {
        FAHRENHEIT,
        CELSIUS
    }

    public static class Stats {
        public final float average;
        public final float min;
        public final float max;

        public Stats(float average, float min, float max) {
            this.average = average;
            this.min = min;
            this.max = max;
        }
    }

    public static Stats getStatistics(List<Float> numbers) {
        return getStatistics(numbers, TemperatureUnit.FAHRENHEIT);
    }

    public static Stats getStatistics(List<Float> numbers, TemperatureUnit unit) {
        if (numbers == null || numbers.isEmpty()) {
            return new Stats(Float.NaN, Float.NaN, Float.NaN);
        }

        float sum = 0f;
        float firstTemperature = convertToFahrenheit(numbers.get(0), unit);
        float min = firstTemperature;
        float max = firstTemperature;

        for (Float number : numbers) {
            float temperatureInFahrenheit = convertToFahrenheit(number, unit);
            sum += temperatureInFahrenheit;
            if (temperatureInFahrenheit < min) {
                min = temperatureInFahrenheit;
            }
            if (temperatureInFahrenheit > max) {
                max = temperatureInFahrenheit;
            }
        }

        float average = sum / numbers.size();
        return new Stats(average, min, max);
    }

    private static float convertToFahrenheit(float temperature, TemperatureUnit unit) {
        if (unit == TemperatureUnit.CELSIUS) {
            return (temperature * 9f / 5f) + 32f;
        }

        return temperature;
    }
}
