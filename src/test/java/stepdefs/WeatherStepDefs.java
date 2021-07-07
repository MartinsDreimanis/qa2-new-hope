package stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import model.Weather;
import model.WeatherResponse;
import org.junit.jupiter.api.Assertions;
import requesters.WeatherRequester;

import java.util.List;
import java.util.Map;

public class WeatherStepDefs {
    private long cityId;
    private WeatherResponse response;

    @Given("city ID: {long}")
    public void set_city_id(long cityId) {
        this.cityId = cityId;
    }

    @When("we are requesting weather data")
    public void request_weather() throws JsonProcessingException {
        WeatherRequester requester = new WeatherRequester();
        response = requester.getWeatherData(cityId);
    }

    @Then("coordinates are:")
    public  void  check_coords(Map<String, Double> coords) {
        Assertions.assertEquals(coords.get("lon"), response.getCoord().getLon(), "Incorrect Coord lon");
        Assertions.assertEquals(coords.get("lat"), response.getCoord().getLat(), "Incorrect Coord lat");
    }

    @And("weather description is:")
    public void check_weather_table(Map<String, String> weather){
        List<Weather> list = response.getWeathers();
        Assertions.assertEquals(Long.parseLong(weather.get("id")), list.get(0).getId(), "Incorrect ID");
        Assertions.assertEquals(weather.get("main"), list.get(0).getMain(), "Incorrect Main");
        Assertions.assertEquals(weather.get("description"), list.get(0).getDescription(), "Incorrect Description");
        Assertions.assertEquals(weather.get("icon"), list.get(0).getIcon(), "Incorrect Icon");
    }

    @And("base is {string}")
    public void check_base(String base) {
        Assertions.assertEquals(base, response.getBase(), "Incorrect Base");
    }

    @And("main readings are:")
    public void check_main(Map<String, String> main){
        Assertions.assertEquals(Double.parseDouble(main.get("temp")), response.getMain().getTemp(), "Incorrect Temp");
        Assertions.assertEquals(Integer.parseInt(main.get("pressure")), response.getMain().getPressure(), "Incorrect Pressure");
        Assertions.assertEquals(Integer.parseInt(main.get("humidity")), response.getMain().getHumidity(), "Incorrect Humidity");
        Assertions.assertEquals(Double.parseDouble(main.get("temp_min")), response.getMain().getTemp_min(), "Incorrect \"temp_min\"");
        Assertions.assertEquals(Double.parseDouble(main.get("temp_max")), response.getMain().getTemp_max(), "Incorrect \"temp_max\"");
    }

    @And("visibility is {int}")
    public void check_visibility(int visibility) {
        Assertions.assertEquals(visibility, response.getVisibility(), "Incorrect Visibility");
    }

    @And("wind readings are:")
    public void check_wind(Map<String, String> wind){
        Assertions.assertEquals(Double.parseDouble(wind.get("speed")), response.getWind().getSpeed(), "Incorrect Speed");
        Assertions.assertEquals(Integer.parseInt(wind.get("deg")), response.getWind().getAngle(), "Incorrect Angle");
    }

    @And("cloud coverage is:")
    public void check_clouds(Map<String, String> clouds){
        Assertions.assertEquals(Integer.parseInt(clouds.get("all")), response.getClouds().getAll(), "Incorrect Clouds");
    }

    @And("dt is {int}")
    public void check_dt(int dt){
        Assertions.assertEquals(dt, response.getDt(), "Incorrect dt");
    }

    @And("system data is:")
    public void check_system (Map<String, String> system){
        Assertions.assertEquals(Integer.parseInt(system.get("type")), response.getSys().getType(), "Incorrect Type");
        Assertions.assertEquals(Integer.parseInt(system.get("id")), response.getSys().getId(), "Incorrect ID");
        Assertions.assertEquals(Double.parseDouble(system.get("message")), response.getSys().getMessage(), "Incorrect Message");
        Assertions.assertEquals(system.get("country"), response.getSys().getCountry(), "Incorrect Country");
        Assertions.assertEquals(Long.parseLong(system.get("sunrise")), response.getSys().getSunrise(), "Incorrect Sunrise");
        Assertions.assertEquals(Long.parseLong(system.get("sunset")), response.getSys().getSunset(), "Incorrect Sunset");
    }

    @And("id is {int}")
    public void check_id(int id){
        Assertions.assertEquals(id, response.getId(), "Incorrect ID");
    }

    @And("name is {string}")
    public void check_name(String name){
        Assertions.assertEquals(name, response.getName(), "Incorrect Name");
    }

    @And("cod is {int}")
    public void check_cod(int cod){
        Assertions.assertEquals(cod, response.getDt(), "Incorrect \"cod\"");
    }
}
