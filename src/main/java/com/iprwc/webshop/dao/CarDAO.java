package com.iprwc.webshop.dao;

import com.iprwc.webshop.model.Car;
import com.iprwc.webshop.repositories.CarRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CarDAO {

    private final CarRepository carRepository;

    public CarDAO(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Returns a product object with a specific id.
     * If there is no product with the specified id, returns null
     *
     * @param id the id of the product
     * @return product
     */
    public Car show(Integer id) {
        Optional<Car> product = this.carRepository.findById(id);

        return product.orElse(null);
    }

    public Car store(Car car){
        return carRepository.save(car);
    }

    public Car update(Car car) {
        this.carRepository.update(car.getTitle(),  car.getDescription(), car.getCategory(), car.getManufacturer(), car.getEngine_displacement(), car.getCylinders(), car.getYear(), car.getKm(), car.getThumbnailUri(), car.getId());
        return car;
    }
}