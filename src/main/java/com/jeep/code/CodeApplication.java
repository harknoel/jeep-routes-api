package com.jeep.code;

import com.jeep.code.model.JeepCode;
import com.jeep.code.model.Place;
import com.jeep.code.repository.JeepCodeRepository;
import com.jeep.code.repository.PlaceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@SpringBootApplication
public class CodeApplication implements CommandLineRunner {
	private final PlaceRepository placeRepository;

	private final JeepCodeRepository jeepCodeRepository;

    public CodeApplication(PlaceRepository placeRepository, JeepCodeRepository jeepCodeRepository) {
        this.placeRepository = placeRepository;
        this.jeepCodeRepository = jeepCodeRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(CodeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Map<String, List<String>> codeMap = Map.ofEntries(
				entry("01A", List.of("Alpha", "Bravo", "Charlie", "Echo", "Golf")),
				entry("02B", List.of("Alpha", "Delta", "Echo", "Foxtrot", "Golf")),
				entry("03C", List.of("Charlie", "Delta", "Foxtrot", "Hotel", "India")),
				entry("04A", List.of("Charlie", "Delta", "Echo", "Foxtrot", "Golf")),
				entry("04D", List.of("Charlie", "Echo", "Foxtrot", "Golf")),
				entry("06B", List.of("Delta", "Hotel", "Juliet", "Kilo", "Lima")),
				entry("06D", List.of("Delta", "Foxtrot", "Golf", "India", "Kilo")),
				entry("10C", List.of("Foxtrot", "Golf", "Hotel", "India", "Juliet")),
				entry("10H", List.of("Foxtrot", "Hotel", "Juliet", "Lima", "November")),
				entry("11A", List.of("Foxtrot", "Golf", "Kilo", "Mike", "November")),
				entry("20A", List.of("India", "Juliet", "November", "Papa", "Romeo")),
				entry("20C", List.of("India", "Kilo", "Lima", "Mike", "Romeo")),
				entry("42C", List.of("Juliet", "Kilo", "Lima", "Mike", "Oscar")),
				entry("42D", List.of("Juliet", "November", "Oscar", "Quebec", "Romeo"))
		);

		codeMap.forEach((code, places) -> {
			JeepCode jeepCode = new JeepCode();
			jeepCode.setCode(code);

			jeepCode = jeepCodeRepository.save(jeepCode);

			JeepCode finalJeepCode = jeepCode;
			places.forEach(placeName -> {
				var place = Place
						.builder()
						.placeName(placeName)
						.jeepCode(finalJeepCode)
						.build();
				placeRepository.save(place);
			});
			jeepCodeRepository.save(jeepCode);
		});

		System.out.println("Codes added successfully.");
	}
}
